package com.qingqing.test.util;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhujianxing on 2018/9/26.
 */
public class QingParamUtil {

    public static void main(String[] args) throws ClassNotFoundException, IntrospectionException, IOException {
//        String className = "com.qingqing.test.bean.ordercourse.request.StartClassRequest";
//        Class<?> clazz = com.qingqing.api.proto.v1.order.Order.GroupSubOrderInfoDetailV2Response.class;
//        System.out.println(generateParamJson(Class.forName(SimpleRepeatedApiProtocolTypeRequest.class.getName()), ""));
//        System.out.println(generateParamJson(com.qingqing.api.proto.bi.BiDataProviderProto.BIDataUnit.class.getName()));
//        System.out.println(JsonUtil.format(JsonFormat
//                .printToString(SimpleBoolRequest.newBuilder().setData(true).build())));

        genProtoClassName("F:\\work\\student_pool_svc\\protobean\\src\\main\\resources\\proto-sub", "D:\\sql\\full-proto.sql");
    }
    private static void genProtoClassName(String dirPath, String outputFileName) throws IOException {
        List<QingProtoFile> qingProtoFiles = parseProtoDir(new File(dirPath));

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        writer.write("delete from `t_proto_class_name`;");
        for (QingProtoFile qingProtoFile : qingProtoFiles) {
            String fullOuterClassName = qingProtoFile.getPackageName() + "." + qingProtoFile.getOutterClassName();
            for (String innerClassName : qingProtoFile.getInnerClassNames()) {
                writer.newLine();
                writer.write(String.format("insert into t_proto_class_name(simple_name, full_class_name, is_deleted, create_time) values('%s', '%s', 0, now());", innerClassName, fullOuterClassName + "$" + innerClassName));
            }
        }

        writer.close();
    }

    private static final Pattern JAVA_PACKAGE_FORMAT = Pattern.compile("\\s*option\\s+java_package\\s*=\\s*\"([^\"]+)\".*");
    private static final Pattern OUTER_CLASS_FORMAT = Pattern.compile("\\s*option\\s+java_outer_classname\\s*=\\s*\"([^\"]+)\".*");
    private static final Pattern INNER_CLASS_FORMAT = Pattern.compile("\\s*message\\s+([^{]+)\\{.*");

    public static List<QingProtoFile> parseProtoDir(File director) throws IOException{
        List<QingProtoFile> resultList = new LinkedList<>();

        File[] fileList = director.listFiles();
        for(File file : fileList){
            if(file.isDirectory()){
                resultList.addAll(parseProtoDir(file));
            }else if(file.getName().endsWith(".proto")){
                resultList.add(parseProtoFile(file));
            }
        }

        return resultList;
    }

    public static QingProtoFile parseProtoFile(File file) throws IOException{
        QingProtoFile result = new QingProtoFile();

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            List<String> innerClassNameList = new ArrayList<>(100);
            while((line = reader.readLine()) != null){
                if(result.getPackageName() == null){
                    Matcher matchResult = JAVA_PACKAGE_FORMAT.matcher(line);
                    if(matchResult.matches()){
                        result.setPackageName(matchResult.group(1).trim());
                    }
                }

                if(result.getOutterClassName() == null){
                    Matcher matchResult = OUTER_CLASS_FORMAT.matcher(line);
                    if(matchResult.matches()){
                        result.setOutterClassName(matchResult.group(1).trim());
                    }
                }

                Matcher matchResult = INNER_CLASS_FORMAT.matcher(line);
                if(matchResult.matches()){
                    innerClassNameList.add(matchResult.group(1).trim());
                }
            }

            result.setInnerClassNames(innerClassNameList);
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    // ignore
                }
            }
        }

        return result;
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
                            result.append(toProperties(properties, genericClazz, true));
                        }else if(((Class) fc).isAssignableFrom(com.google.protobuf.ProtocolStringList.class)){
                            result.append(toProperties(properties, String.class, true));
                        }
                    }else{
                        Class<?> propertiesType = (Class<?>) pd.getReadMethod().getGenericReturnType();
                        result.append(toProperties(properties, propertiesType, false));
                    }
                    result.append(",");
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
            }
        }

        String s = result.toString();
        return s.substring(0, s.length() - 1);
    }

    private static String toProperties(String properties, Class propertiesType, boolean isArray) throws IntrospectionException, ClassNotFoundException {
        if(isSimpleType(propertiesType)){
            if(String.class.equals(propertiesType)){
                return toStringProperties(properties, isArray);
            }else if(boolean.class.equals(propertiesType)){
                return toBooleanProperties(properties, isArray);
            }else{
                return toNumberProperties(properties, isArray);
            }
        }else{
            return toObjectOrEnumProperties(properties, propertiesType, isArray);
        }
    }

    private static String toObjectOrEnumProperties(String properties, Class<?> enumClazz, boolean isArray) throws IntrospectionException, ClassNotFoundException {
        StringBuilder result = new StringBuilder();
        String defaultValue = null;
        boolean isEnum = false;
        for(Field field : enumClazz.getDeclaredFields()) {
            if ((Modifier.STATIC & field.getModifiers()) == Modifier.STATIC) {
                if(!"DEFAULT_INSTANCE".equals(field.getName()) && enumClazz.equals(field.getType())){
                    isEnum = true;
                    String select = String.format("{\"name\":\"%s\",\"value\":\"%s\"}", field.getName(), field.getName());
                    if(defaultValue == null){
                        defaultValue = select;
                    }
                    result.append(select);
                    result.append(",");
                    System.out.println(field.getName() + "-----" + field.getType().getName());
                }
            }
        }

        if(isEnum){
            String selectable = result.toString();
            selectable = selectable.substring(0, selectable.length() - 1);
            if(isArray){
                return String.format("[{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":[%s],\"selectable\":[%s]}]", properties, properties, defaultValue, selectable);
            }else{
                return String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":%s,\"selectable\":[%s]}", properties, properties, defaultValue, selectable);
            }
        }else{
            return toObjectProperties(properties, enumClazz, isArray);
        }
    }

    private static String toStringProperties(String properties, boolean isArray){
        if(isArray){
            return  String.format("[{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":[\"%s\"]}]", properties, properties, properties);
        }else{
            return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":\"%s\"}", properties, properties, properties);
        }
    }

    private static String toBooleanProperties(String properties, boolean isArray){
        if(isArray){
            return  String.format("[{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":[{\"name\":\"否\",\"value\":\"false\"}], \"class\":\"switch_editable\"}]", properties, properties, properties);
        }else{
            return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":{\"name\":\"否\",\"value\":\"false\"}, \"class\":\"switch_editable\"}", properties, properties, properties);
        }
    }

    private static String toNumberProperties(String properties, boolean isArray){
        if(isArray){
            return  String.format("[{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":[1]}]", properties, properties);
        }else{
            return  String.format("{\"key\":\"%s\",\"name\":\"%s\",\"defaultValue\":1}", properties, properties);
        }
    }

    private static String toObjectProperties(String properties, Class<?> subTypeClass, boolean isArray) throws IntrospectionException, ClassNotFoundException {
        String subType = generateParamJson(subTypeClass, "\t\t\t");
        if(isArray){
            return String.format("[{\"key\":\"%s\",\"name\":\"%s\",\"detail\":[[%s]]}]", properties, properties, subType);
        }else{
            return String.format("{\"key\":\"%s\",\"name\":\"%s\",\"detail\":[%s]}", properties, properties, subType);
        }
    }

    static class QingProtoFile{
        private String packageName;
        private String outterClassName;
        private List<String> innerClassNames;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getOutterClassName() {
            return outterClassName;
        }

        public void setOutterClassName(String outterClassName) {
            this.outterClassName = outterClassName;
        }

        public List<String> getInnerClassNames() {
            return innerClassNames;
        }

        public void setInnerClassNames(List<String> innerClassNames) {
            this.innerClassNames = innerClassNames;
        }
    }

    private static boolean isSimpleType(Class<?> enumClazz){
        return String.class.equals(enumClazz) || enumClazz.isPrimitive() || Number.class.isAssignableFrom(enumClazz);
    }
}
