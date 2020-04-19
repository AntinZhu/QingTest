package ${basePackage}.aspect.mock;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message.Builder;
import com.googlecode.protobuf.format.JsonFormat;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.web.manager.HttpClientManagerV2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 目前支持一下类型的返回值的mock:
 *          > 枚举值
 *          > String
 *          > 正常的BEAN
 *          > void
 *          >List
 * 待支持：
 *         > 基本数据类型(已实现，待测试)
 *         > 基本数据类型封装类(已实现，待测试)
 *         > protobuf(已实现，待测试)
 *         > io流
 *
 * Created by ${user!'test-api'} on ${date}
 */
@Aspect
public class QingMockAspect {

    private static final Logger logger = LoggerFactory.getLogger(QingMockAspect.class);
    private static final String SWITCH_KEY_PREFIX = "api_mock_enable_";
    private static final String NOT_MOCK_RESULT = "qing_not_mock";

    @Autowired
    private HttpClientManagerV2 httpClientManagerV2;
    @Value("${"$"}{api.common.mock.url:http://172.20.13.160:8090/apiTest/v1/mock/invoke}")
    private String mockUrl;

    @Around(value = "@annotation(qingMock)", argNames = "qingMock")
    public Object around(final ProceedingJoinPoint pjp, QingMock qingMock) throws Throwable {
        String mockParam;
        if(qingMock.ruleParamIndex() >= 0){
            mockParam = String.valueOf(ExpressionUtils.getValue(pjp.getArgs()[qingMock.ruleParamIndex()], qingMock.ruleParamExpression()));
        }else{
            mockParam = "";
        }

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("mockType", qingMock.type().name());
        paramMap.put("mockParam", mockParam);

        String mockResult;
        if(!StringUtils.isEmpty(qingMock.fixedResult())){
            mockResult = qingMock.fixedResult();
        }else{
            try{
                mockResult = httpClientManagerV2.execPostFormDataDefaultContentType(mockUrl, paramMap);
            }catch(Exception e){
                logger.error("mock fail for type:" + qingMock.type(), e);
                // mock服务不可用时，走原代码逻辑
                return pjp.proceed();
            }

            // mock接口返回不mock，走原代码逻辑
            if(NOT_MOCK_RESULT.equals(mockResult)){
                return pjp.proceed();
            }
        }

        logger.info("user mock result for {}, resp:{}", qingMock.type(), mockResult);
        // 转换返回值
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Type genericReturnType = method.getGenericReturnType();
        return convertReturnValue(mockResult, method.getReturnType(),genericReturnType);
    }

    private Object convertReturnValue(String mockResp, Class<?> returnClass,Type genericReturnType ) throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        if(void.class.equals(returnClass) || Void.class.equals(returnClass)){
            return null;
        }

        // 枚举
        if(returnClass.isEnum()){
            return Enum.valueOf((Class<Enum>)returnClass, mockResp);
        }
        // String
        if(String.class.equals(returnClass)){
            return mockResp;
        }
        // bool
        if(boolean.class.equals(returnClass) || Boolean.class.equals(returnClass)){
            return Boolean.valueOf(mockResp);
        }
        // int
        if(int.class.equals(returnClass) || Integer.class.equals(returnClass)){
            return Integer.valueOf(mockResp);
        }
        // Long
        if(long.class.equals(returnClass) || Long.class.equals(returnClass)){
            return Long.valueOf(mockResp);
        }
        // Double
        if(double.class.equals(returnClass) || Double.class.equals(returnClass)){
            return Double.valueOf(mockResp);
        }
        // short
        if(short.class.equals(returnClass) || Short.class.equals(returnClass)){
            return Short.valueOf(mockResp);
        }
        // byte
        if(byte.class.equals(returnClass) || Byte.class.equals(returnClass)){
            return Byte.valueOf(mockResp);
        }
        // float
        if(float.class.equals(returnClass) || Float.class.equals(returnClass)){
            return Byte.valueOf(mockResp);
        }
        // char
        if(char.class.equals(returnClass) || Character.class.equals(returnClass)){
            return Character.valueOf(mockResp.charAt(0));
        }

        // proto优先
        Object protoResult = convertToProto(mockResp, returnClass);
        if(protoResult != null){
            return protoResult;
        }

        if(List.class.isAssignableFrom(returnClass)){//如果return Class 是List  类或其子类
            if(genericReturnType != null) {
                // 如果是泛型参数的类型
                if(genericReturnType instanceof ParameterizedType){
                    ParameterizedType pt = (ParameterizedType) genericReturnType;
                    //得到泛型里的class类型对象
                    Class<?> genericClazz = (Class<?>)pt.getActualTypeArguments()[0];
                    return com.alibaba.fastjson.JSON.parseArray(mockResp, genericClazz);
                }
            }
        }
        return com.alibaba.fastjson.JSON.parseObject(mockResp, returnClass);
    }

    private Object convertToProto(String jsonValue, Class<?> protoClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        Method protoBuilder = null;
        try{
            protoBuilder = protoClass.getMethod("newBuilder", new Class[0]);
            if(protoBuilder == null){
                return null;
            }
        }catch (Exception e){
            return null;
        }
        Builder builder = (Builder)protoBuilder.invoke(protoClass, new Object[0]);
        JsonFormat.merge(new InputStreamReader(new ByteArrayInputStream(jsonValue.getBytes())), ExtensionRegistry.newInstance(), builder);

        return builder.build();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
//        Builder builder = (Builder)SimpleQingQingIdRequest.class.getMethod("newBuilder", new Class[0]).invoke(SimpleQingQingIdRequest.class, new Object[0]);
//        JsonFormat.merge(new InputStreamReader(new ByteArrayInputStream("{\"qingqing_id\":\"123\"}".getBytes())), ExtensionRegistry.newInstance(), builder);
//
//        System.out.println(JsonUtil.format(JsonFormat.printToString(builder.build())));
    }

    private static class ExpressionUtils {

        public static Object getValue(Object model, String expression){
            if(StringUtils.isEmpty(expression)){
                return model;
            }

            return getValue(model, expression, false);
        }

        public static Object getValue(Object model, String expression, boolean throwEx){
            StandardEvaluationContext context= new StandardEvaluationContext();
            context.setRootObject(model);
            PropertyAccessor accessor1 = new MapAccessor();
            PropertyAccessor accessor2 = new ReflectivePropertyAccessor();
            List<PropertyAccessor> propertyAccessors = new ArrayList<PropertyAccessor>();
            propertyAccessors.add(accessor1);
            propertyAccessors.add(accessor2);
            context.setPropertyAccessors(propertyAccessors);
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(expression);
            try{
                return exp.getValue(context);
            }catch(SpelEvaluationException e){
                if (throwEx){
                    throw new RuntimeException(e.getMessage(), e);
                }else{
                    return null;
                }
            }
        }

        public static void setValue(Object model, String expression, Object value){
            StandardEvaluationContext context= new StandardEvaluationContext();
            context.setRootObject(model);
            PropertyAccessor accessor1 = new MapAccessor();
            PropertyAccessor accessor2 = new ReflectivePropertyAccessor();
            List<PropertyAccessor> propertyAccessors = new ArrayList<PropertyAccessor>();
            propertyAccessors.add(accessor1);
            propertyAccessors.add(accessor2);
            context.setPropertyAccessors(propertyAccessors);
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(expression);
            String[] expr = expression.split("\\.");
            Object nodeModel = model;//遍历时节点索引时的model
            for (int i=0; i<expr.length - 1; i++){
                if (expr[i].matches(".+\\[\\d+\\]$")){
                    nodeModel = doListExpression(expr[i], nodeModel);
                }else{
                    nodeModel = doMapExpression(expr[i], nodeModel);
                }
            }
            exp.setValue(context, value);
        }

        @SuppressWarnings("unchecked")
        private static Object doMapExpression(String expr, Object nodeModel) {
            //map
            Map map = (Map)ExpressionUtils.getValue(nodeModel, expr);
            if (map == null){
                map = new HashMap();
                ExpressionUtils.setValue(nodeModel, expr, map);
            }
            return map;
        }

        @SuppressWarnings("unchecked")
        private static Object doListExpression(String expr, Object nodeModel) {
            String property = expr.substring(0, expr.indexOf("["));
            int index = Integer.parseInt(expr.substring(expr.indexOf("[") + 1, expr.length() - 1));
            List list = (List)ExpressionUtils.getValue(nodeModel, property);
            if (list == null){
                list = new ArrayList();
                Map item = new HashMap();
                list.add(item);
                ExpressionUtils.setValue(nodeModel, property, list);

                return item;
            }else{
                Map item = null;
                if (index >= list.size()){
                    item = new HashMap();
                    list.add(index, item);
                }else{
                    item = (Map)list.get(index);
                }

                return item;
            }
        }
    }
}
