package com.qingqing.test.spring;

import com.google.common.collect.Lists;
import com.qingqing.common.util.CollectionsUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/1/11.
 */
public class QingOnAllBeanCondition extends SpringBootCondition implements ConfigurationCondition {

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
                                            AnnotatedTypeMetadata metadata) {
        ConditionMessage matchMessage = ConditionMessage.empty();
        QingOnAllBeanCondition.BeanSearchSpec spec = new QingOnAllBeanCondition.BeanSearchSpec(context, metadata,
                QingConditionalOnBean.class);
        List<String> existBeanNames = getMatchingBeansName(context, spec);
        if (existBeanNames.size() != spec.needBeanCount()) {
            return ConditionOutcome.noMatch(
                    ConditionMessage.forCondition(ConditionalOnBean.class, spec)
                            .didNotFind("not match all beans").atAll());
        }
        matchMessage = matchMessage.andCondition(ConditionalOnBean.class, spec)
                    .found("bean", "beans").items(Style.QUOTE, existBeanNames);

        return ConditionOutcome.match(matchMessage);
    }

    @SuppressWarnings("deprecation")
    private List<String> getMatchingBeansName(ConditionContext context,
                                                             QingOnAllBeanCondition.BeanSearchSpec beans) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        if (beans.getStrategy() == SearchStrategy.PARENTS
                || beans.getStrategy() == SearchStrategy.ANCESTORS) {
            BeanFactory parent = beanFactory.getParentBeanFactory();
            Assert.isInstanceOf(ConfigurableListableBeanFactory.class, parent,
                    "Unable to use SearchStrategy.PARENTS");
            beanFactory = (ConfigurableListableBeanFactory) parent;
        }
        if (beanFactory == null) {
            return Collections.emptyList();
        }
        List<String> beanNames = new ArrayList<>();
        boolean considerHierarchy = beans.getStrategy() != SearchStrategy.CURRENT;
        for (String type : beans.getTypes()) {
            Collection<String> collectionBeans = getBeanNamesForType(beanFactory, type,
                    context.getClassLoader(), considerHierarchy);
            if (!CollectionsUtil.isNullOrEmpty(collectionBeans)) {
                beanNames.add(type);
            }
        }

        for (String beanName : beans.getNames()) {
            if (containsBean(beanFactory, beanName, considerHierarchy)) {
                beanNames.add(beanName);
            }
        }

        return beanNames;
    }

    private boolean containsBean(ConfigurableListableBeanFactory beanFactory,
                                 String beanName, boolean considerHierarchy) {
        if (considerHierarchy) {
            return beanFactory.containsBean(beanName);
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private Collection<String> getBeanNamesForType(ListableBeanFactory beanFactory,
                                                   String type, ClassLoader classLoader, boolean considerHierarchy)
            throws LinkageError {
        try {
            Set<String> result = new LinkedHashSet<String>();
            collectBeanNamesForType(result, beanFactory,
                    ClassUtils.forName(type, classLoader), considerHierarchy);
            return result;
        }
        catch (ClassNotFoundException ex) {
            return Collections.emptySet();
        }
        catch (NoClassDefFoundError ex) {
            return Collections.emptySet();
        }
    }

    private void collectBeanNamesForType(Set<String> result,
                                         ListableBeanFactory beanFactory, Class<?> type, boolean considerHierarchy) {
        result.addAll(Lists.newArrayList(beanFactory.getBeanNamesForType(type)));
        if (considerHierarchy && beanFactory instanceof HierarchicalBeanFactory) {
            BeanFactory parent = ((HierarchicalBeanFactory) beanFactory)
                    .getParentBeanFactory();
            if (parent instanceof ListableBeanFactory) {
                collectBeanNamesForType(result, (ListableBeanFactory) parent, type,
                        considerHierarchy);
            }
        }
    }

    private static class BeanSearchSpec {

        private final Class<?> annotationType;
        private final List<String> names = new ArrayList<String>();
        private final List<String> types = new ArrayList<String>();
        private final SearchStrategy strategy;

        BeanSearchSpec(ConditionContext context, AnnotatedTypeMetadata metadata,
                       Class<?> annotationType) {
            this.annotationType = annotationType;
            MultiValueMap<String, Object> attributes = metadata
                    .getAllAnnotationAttributes(annotationType.getName(), true);
            collect(attributes, "value", this.types);
            collect(attributes, "name", this.names);
            this.strategy = (SearchStrategy) metadata
                    .getAnnotationAttributes(annotationType.getName()).get("search");
            QingOnAllBeanCondition.BeanTypeDeductionException deductionException = null;
            try {
                if (this.types.isEmpty()) {
                    addDeducedBeanType(context, metadata, this.types);
                }
            }
            catch (QingOnAllBeanCondition.BeanTypeDeductionException ex) {
                deductionException = ex;
            }
            validate(deductionException);
        }

        protected void validate(QingOnAllBeanCondition.BeanTypeDeductionException ex) {
            if (!hasAtLeastOne(this.types, this.names)) {
                String message = annotationName()
                        + " did not specify a bean using type, name or annotation";
                if (ex == null) {
                    throw new IllegalStateException(message);
                }
                throw new IllegalStateException(message + " and the attempt to deduce"
                        + " the bean's type failed", ex);
            }
        }

        private boolean hasAtLeastOne(List<?>... lists) {
            for (List<?> list : lists) {
                if (!list.isEmpty()) {
                    return true;
                }
            }
            return false;
        }

        protected String annotationName() {
            return "@" + ClassUtils.getShortName(this.annotationType);
        }

        protected void collect(MultiValueMap<String, Object> attributes, String key,
                               List<String> destination) {
            List<?> values = attributes.get(key);
            if (values != null) {
                for (Object value : values) {
                    if (value instanceof String[]) {
                        Collections.addAll(destination, (String[]) value);
                    }
                    else {
                        destination.add((String) value);
                    }
                }
            }
        }

        private void addDeducedBeanType(ConditionContext context,
                                        AnnotatedTypeMetadata metadata, final List<String> beanTypes) {
            if (metadata instanceof MethodMetadata
                    && metadata.isAnnotated(Bean.class.getName())) {
                addDeducedBeanTypeForBeanMethod(context, (MethodMetadata) metadata,
                        beanTypes);
            }
        }

        private void addDeducedBeanTypeForBeanMethod(ConditionContext context,
                                                     MethodMetadata metadata, final List<String> beanTypes) {
            try {
                // We should be safe to load at this point since we are in the
                // REGISTER_BEAN phase
                Class<?> returnType = ClassUtils.forName(metadata.getReturnTypeName(),
                        context.getClassLoader());
                beanTypes.add(returnType.getName());
            }
            catch (Throwable ex) {
                throw new QingOnAllBeanCondition.BeanTypeDeductionException(metadata.getDeclaringClassName(),
                        metadata.getMethodName(), ex);
            }
        }

        public SearchStrategy getStrategy() {
            return (this.strategy != null ? this.strategy : SearchStrategy.ALL);
        }

        public List<String> getTypes() {
            return this.types;
        }

        @Override
        public String toString() {
            StringBuilder string = new StringBuilder();
            string.append("(");
            if (!this.types.isEmpty()) {
                string.append("types: ");
                string.append(StringUtils.collectionToCommaDelimitedString(this.types));
            }
            string.append("; SearchStrategy: ");
            string.append(this.strategy.toString().toLowerCase());
            string.append(")");
            return string.toString();
        }

        public List<String> getNames() {
            return names;
        }

        public Integer needBeanCount(){
            return names.size() + types.size();
        }
    }

    static final class BeanTypeDeductionException extends RuntimeException {
        private BeanTypeDeductionException(String className, String beanMethodName,
                                           Throwable cause) {
            super("Failed to deduce bean type for " + className + "." + beanMethodName,
                    cause);
        }

    }
}
