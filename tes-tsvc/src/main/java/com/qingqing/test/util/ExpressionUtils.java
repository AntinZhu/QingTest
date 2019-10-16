package com.qingqing.test.util;

import com.qingqing.test.bean.common.UrlAndParam;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/10/16.
 */
public class ExpressionUtils {

    public static Object getValue(Object model, String expression){
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


    public static void main(String[] args) {
        UrlAndParam urlAndParam = new UrlAndParam();
        urlAndParam.setUrl("abc");
        urlAndParam.setParam("df");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("param", urlAndParam);
        System.out.println(ExpressionUtils.getValue(paramMap, "['param'].url"));
    }
}
