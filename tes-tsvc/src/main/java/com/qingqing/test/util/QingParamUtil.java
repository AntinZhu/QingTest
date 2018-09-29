package com.qingqing.test.util;

import com.qingqing.api.proto.v1.Pay;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zhujianxing on 2018/9/26.
 */
public class QingParamUtil {

    public static void main(String[] args) throws ClassNotFoundException, IntrospectionException {
//        String className = "com.qingqing.test.bean.ordercourse.request.StartClassRequest";
//        Class<?> clazz = com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response.class;
//        System.out.println(generateParamJson(Class.forName(TeachingTimeAndClassTimeRequest.class.getName()), ""));
        System.out.println(Pay.GeneralOrderPaymentSummaryV3Request.class.getName());
    }

    public static String generateParamJson(String className){
        Class clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new ErrorCodeException(new SimpleErrorCode(1001, "class not found","未找到对应的类"), "class not found");
        }

        try {
            return "[" + generateParamJson(clazz, "") + "]";
        } catch (Exception e) {
            throw new ErrorCodeException(new SimpleErrorCode(1001, "class not found","类解析错误"), "class not found", e);
        }
    }

    private static String generateParamJson(Class<?> clazz, String prefix) throws ClassNotFoundException, IntrospectionException {
        if(clazz.isPrimitive() || Object.class.equals(clazz)){
            return "";
        }

        StringBuilder result = new StringBuilder();
        for(Field field : clazz.getDeclaredFields()){
            if((Modifier.STATIC & field.getModifiers()) == 0){
                String properties = field.getName().substring(0, field.getName().length() - 1);

                PropertyDescriptor pd = null;
                try{
                    pd = new PropertyDescriptor(properties, clazz, QingStringUtil.generateGetMethod(properties), null);
                }catch(IntrospectionException e){
                    try{
                        pd = new PropertyDescriptor(properties, clazz, QingStringUtil.generateGetListMethod(properties), null);
                    }catch(IntrospectionException ef) {
                        continue;
                    }
                }
                if(pd != null){
                    properties = QingStringUtil.toUnderlineStyle(properties);
                    if(List.class.isAssignableFrom(field.getType())) { // list
                        Type fc = pd.getReadMethod().getGenericReturnType(); // 关键的地方，如果是List类型，得到其Generic的类型
                        if(fc == null) continue;

                        if(fc instanceof ParameterizedType){
                            ParameterizedType pt = (ParameterizedType) fc;
                            Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
                            System.out.println("\t\t --- >" + genericClazz.getName());
                            result.append("[");
                            result.append(toProperties(properties, genericClazz));
                            result.append("]");
                        }
                    }else{
                        Class<?> propertiesType = (Class<?>) pd.getReadMethod().getGenericReturnType();
                        result.append(toProperties(properties, propertiesType));
                    }
                }

//                if(field.getType().isPrimitive() || Object.class.equals(field.getType())){ // String及基本数据类型
//                    if(pd != null ){
//                        System.out.println(prefix + properties + "->" + field.getType().getName());
//                        Class<?> propertiesType = (Class<?>) pd.getReadMethod().getGenericReturnType();
//                        result.append(toProperties(properties, propertiesType));
//                        System.out.println(prefix + "[" + propertiesType + "]");
//                    }
//                }else{
//                    System.out.println(prefix + properties + "->" + field.getType().getName());
//                    if(field.getType().isAssignableFrom(List.class)){ // list
//                        Type fc = pd.getReadMethod().getGenericReturnType(); // 关键的地方，如果是List类型，得到其Generic的类型
//                        if(fc == null) continue;
//
//                        if(fc instanceof ParameterizedType){
//                            ParameterizedType pt = (ParameterizedType) fc;
//                            Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
//                            System.out.println("\t\t --- >" + genericClazz.getName());
//                            result.append("[");
//                            result.append(toProperties(properties, genericClazz));
//                            result.append("]");
//                        }
//                    }else{ // 单一元素
//                        result.append(toObjectProperties(properties, field.getType()));
//                    }
//                }

                result.append(",");
            }
        }

        String s = result.toString();
        return s.substring(0, s.length() - 1);
    }

    private static String toProperties(String properties, Class propertiesType) throws IntrospectionException, ClassNotFoundException {
        if(String.class.equals(propertiesType) || propertiesType.isPrimitive() || Number.class.isAssignableFrom(propertiesType)){
            if(String.class.equals(propertiesType)){
                return toStringProperties(properties);
            }else{
                return toNumberProperties(properties);
            }
        }else{
            return toObjectOrEnumProperties(properties, propertiesType);
        }
    }

    private static String toObjectOrEnumProperties(String properties, Class<?> enumClazz) throws IntrospectionException, ClassNotFoundException {
        StringBuilder result = new StringBuilder();
        String defaultValue = null;
        boolean isEnum = false;
        for(Field field : enumClazz.getDeclaredFields()) {
            if ((Modifier.STATIC & field.getModifiers()) == Modifier.STATIC) {
                if(enumClazz.equals(field.getType())){
                    isEnum = true;
                    String select = String.format("{\"name\":\"%s\",\"value\":\"%s\"},", field.getName(), field.getName());
                    if(defaultValue == null){
                        defaultValue = select;
                    }
                    result.append(select);
                    System.out.println(field.getName() + "-----" + field.getType().getName());
                }
            }
        }

        if(isEnum){
            String selectable = result.toString();
            selectable = selectable.substring(0, selectable.length() - 1);
            return String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":%s\"selectable\":[%s]}", properties, properties, defaultValue, selectable);
        }else{
            return toObjectProperties(properties, enumClazz);
        }
    }

    private static String toStringProperties(String properties){
        return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":\"%s\"}", properties, properties, properties);
    }

    private static String toNumberProperties(String properties){
        return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":1}", properties, properties);
    }

    private static String toObjectProperties(String properties, Class<?> subTypeClass) throws IntrospectionException, ClassNotFoundException {
        String subType = generateParamJson(subTypeClass, "\t\t\t");
        return String.format("{\"key\":\"%s\",\"name\":\"%s\",\"detail\":[%s]}", properties, properties, subType);
    }
}
