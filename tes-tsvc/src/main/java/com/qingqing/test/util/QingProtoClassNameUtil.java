package com.qingqing.test.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhujianxing on 2019/8/1.
 */
public class QingProtoClassNameUtil {

    public static void main(String[] args) throws ClassNotFoundException,IOException {
        String outFilePath = null;
        String inputFilePath = null;
        String fromServer  = null;
        if(args.length > 2){
            fromServer = args[0];
            inputFilePath = args[1];
            outFilePath = args[2];

            genProtoClassName(fromServer, inputFilePath, outFilePath);
        }else{
            System.out.println("缺少参数");
        }

        genProtoClassName("scoresvc", "D:\\protobuf\\protobuf-scoresvc", "D:\\_1\\work\\Github\\bat\\scoresvc.sql");
    }

    private static void genProtoClassName(String fromServer, String dirPath, String outputFileName) throws IOException {
        List<QingProtoFile> qingProtoFiles = parseProtoDir(new File(dirPath));

        File outputFile = new File(outputFileName);
        if(!outputFile.exists()){
            outputFile.mkdirs();
        }
        outputFile.delete();

        BufferedWriter writer = null;
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(outputFileName);
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("use qq_itest;");
            writer.write("delete from `t_proto_class_name` where from_server = '" + fromServer + "';");
            for (QingProtoFile qingProtoFile : qingProtoFiles) {
                try{
                    String fullOuterClassName = qingProtoFile.getPackageName() + "." + qingProtoFile.getOutterClassName();
                    for (String innerClassName : qingProtoFile.getInnerClassNames()) {
                        writer.newLine();
                        writer.write(String.format("insert into t_proto_class_name(from_server, simple_name, full_class_name, is_deleted, create_time) values('%s','%s', '%s', 0, now());", fromServer, innerClassName, fullOuterClassName + "$" + innerClassName));
                        writer.flush();
                    }
                }catch(Exception e){
                    // ignore
                }
            }
        }finally{
            if(fos != null){
                fos.close();
            }
        }
    }

    private static final Pattern JAVA_PACKAGE_FORMAT = Pattern.compile("\\s*option\\s+java_package\\s*=\\s*\"([^\"]+)\".*");
    private static final Pattern OUTER_CLASS_FORMAT = Pattern.compile("\\s*option\\s+java_outer_classname\\s*=\\s*\"([^\"]+)\".*");
    private static final Pattern INNER_CLASS_FORMAT = Pattern.compile("\\s*message\\s+([^{]+)\\{.*");

    public static List<QingProtoFile> parseProtoDir(File director) throws IOException{
        List<QingProtoFile> resultList = new LinkedList<>();

        File[] fileList = director.listFiles();
        if(fileList == null){
            return Collections.emptyList();
        }

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

