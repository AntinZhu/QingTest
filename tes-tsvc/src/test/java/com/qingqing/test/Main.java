package com.qingqing.test;

import com.qingqing.test.util.QingStringUtil;

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
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IntrospectionException {
//        String className = "com.qingqing.test.bean.ordercourse.request.StartClassRequest";
//        Class<?> clazz = com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response.class;
//        System.out.println(printField(Class.forName(TeachingTimeAndClassTimeRequest.class.getName()), ""));
        Double paidAmount = 6D;
        System.out.println(String.valueOf(paidAmount));
    }

    private static String printField(Class<?> clazz, String prefix) throws ClassNotFoundException, IntrospectionException {
        if(clazz.isPrimitive() || Object.class.equals(clazz)){
            return "";
        }

        StringBuilder result = new StringBuilder();
        for(Field field : clazz.getDeclaredFields()){
            if((Modifier.STATIC & field.getModifiers()) == 0){
                String properties = field.getName().substring(0, field.getName().length() - 1);

                PropertyDescriptor pd = null;
                try{
                    pd = new PropertyDescriptor(properties, clazz, "get" + properties, null);
                }catch(IntrospectionException e){
                    // ignore
                }
                properties = QingStringUtil.toUnderlineStyle(properties);
                if(field.getType().isPrimitive() || Object.class.equals(field.getType())){ // String及基本数据类型
                    if(pd != null ){
                        System.out.println(prefix + properties + "->" + field.getType().getName());
                        Class<?> propertiesType = (Class<?>) pd.getReadMethod().getGenericReturnType();
                        if(String.class.equals(propertiesType) || propertiesType.isPrimitive() || Number.class.isAssignableFrom(propertiesType)){
                            if(String.class.equals(propertiesType)){
                                result.append(toStringProperties(properties));
                            }else{
                                result.append(toNumberProperties(properties));
                            }
                        }else{
                            result.append(toEnumProperties(properties, propertiesType));
                        }
                        System.out.println(prefix + "[" + propertiesType + "]");
                    }
                }else{
                    System.out.println(prefix + properties + "->" + field.getType().getName());
                    if(field.getType().isAssignableFrom(List.class)){ // list
                        Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                        if(fc == null) continue;

                        if(fc instanceof ParameterizedType){
                            ParameterizedType pt = (ParameterizedType) fc;
                            Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
                            System.out.println("\t\t --- >" + genericClazz.getName());
                            if(!genericClazz.isPrimitive() && !Number.class.isAssignableFrom(genericClazz)){
                                result.append(toCollectionProperties(properties, genericClazz));
                            }else{
                                if(Number.class.isAssignableFrom(genericClazz)){
                                    result.append(toNumberProperties(properties));
                                }else{
                                    result.append(toStringProperties(properties));
                                }
                            }
                        }
                    }else{ // 单一元素
                       String subType =  printField(field.getType(), prefix + "\t");
                        result.append(String.format("{\"key\":\"%s\",\"name\":\"%s\",\"detail\":%s},", properties, properties, subType));
                    }
                }
            }
        }

        String s = result.toString();
        return "[" + s.substring(0, s.length() - 1) + "]";
    }

    private static String toEnumProperties(String properties, Class<?> enumClazz){
        StringBuilder result = new StringBuilder();
        String defaultValue = "";
        for(Field field : enumClazz.getDeclaredFields()) {
            if ((Modifier.STATIC & field.getModifiers()) == Modifier.STATIC) {
                if(enumClazz.equals(field.getType())){
                    String select = String.format("{\"name\":\"%s\",\"value\":\"%s\"},", field.getName(), field.getName());
                    defaultValue = select;
                    result.append(select);
                    System.out.println(field.getName() + "-----" + field.getType().getName());
                }
            }
        }

        String selectable = result.toString();
        selectable = selectable.substring(0, selectable.length() - 1);
        return String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":%s\"selectable\":[%s]},", properties, properties, defaultValue, selectable);
    }

    private static String toStringProperties(String properties){
        return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":\"%s\"},", properties, properties, properties);
    }

    private static String toNumberProperties(String properties){
        return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":1},", properties, properties);
    }

    private static String toCollectionProperties(String properties, Class<?> collectionType) throws IntrospectionException, ClassNotFoundException {
        String subType = printField(collectionType, "\t\t\t");
        return String.format("{\"key\":\"%s\",\"name\":\"%s\",\"detail\":[%s]},", properties, properties, subType);
    }
}
