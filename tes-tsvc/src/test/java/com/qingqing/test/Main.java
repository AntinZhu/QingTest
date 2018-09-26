package com.qingqing.test;

import org.assertj.core.util.Sets;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Created by zhujianxing on 2018/9/26.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IntrospectionException {
//        String className = "com.qingqing.test.bean.ordercourse.request.StartClassRequest";
//        Class<?> clazz = com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response.class;
        printField(Class.forName("com.qingqing.api.proto.v1.order.Order$GroupSubOrderInfoDetailV2"), "");
    }

    private static Set<String> stopType =  Sets.newLinkedHashSet("int", "java.lang.Object");
    private static Set<String> excludeProperties = Sets.newLinkedHashSet("bitField0_", "memoizedIsInitialized", "memoizedSerializedSize");

    private static void printField(Class<?> clazz, String prefix) throws ClassNotFoundException, IntrospectionException {
        if(clazz.isPrimitive() || Object.class.equals(clazz)){
            return;
        }

        for(Field field : clazz.getDeclaredFields()){
            if((Modifier.STATIC & field.getModifiers()) == 0){
                String properties = field.getName().substring(0, field.getName().length() - 1);

                PropertyDescriptor pd = null;
                try{
                    pd = new PropertyDescriptor(properties, clazz, "get" + properties, null);
                }catch(IntrospectionException e){
                    // ignore
                }
                if(field.getType().isPrimitive() || Object.class.equals(field.getType())){
                    if(pd != null ){
                        System.out.println(prefix + properties + "->" + field.getType().getName());
                        Class<?> propertiesType = (Class<?>) pd.getReadMethod().getGenericReturnType();
                        System.out.println(prefix + "[" + propertiesType + "]");
                    }
                    continue;
                }
                System.out.println(prefix + properties + "->" + field.getType().getName());
                if(field.getType().isAssignableFrom(List.class)){
                    Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                    if(fc == null) continue;

                    if(fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                    {
                        ParameterizedType pt = (ParameterizedType) fc;
                        Class genericClazz = (Class)pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
                        System.out.println("\t\t --- >" + genericClazz.getName());
                        printField(genericClazz, prefix + "\t\t\t");
                    }
                }else{
                    printField(field.getType(), prefix + "\t");
                }
            }
        }
    }
}
