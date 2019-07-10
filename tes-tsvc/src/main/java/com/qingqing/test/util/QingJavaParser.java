package com.qingqing.test.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.collect.Sets;
import com.qingqing.common.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

/**
 * Created by zhujianxing on 2019/6/4.
 */
public class QingJavaParser {

    public static void main(String[] args) throws Exception{
//        FileInputStream in = new FileInputStream("F:\\work\\Github\\QingTest\\tes-tsvc\\src\\main\\java\\com\\qingqing\\test\\service\\config\\TestConfigService.java");
//
//        // parse the file
//        CompilationUnit cu = JavaParser.parse(in);
//
//
//        // prints the resulting compilation unit to default system output
////        System.out.println(cu.toString());
//
//        cu.accept(new MethodVisitor(), null);
        System.out.println("total:" + checkDir(new File("F:\\work\\backup\\svc\\QQ\\src\\main\\java\\com\\qingqing\\api")));
    }

    private static int checkDir(File dir) throws IOException, SAXException {
        int total = 0;
        File[] xmlFiles = dir.listFiles();
        for (File xmlFile : xmlFiles) {
            if(xmlFile.isDirectory()){
                total += checkDir(xmlFile);
            }else{
                if(xmlFile.getName().endsWith(".java")){
                    total++;
                    FileInputStream in = null;
                    try{
                        in = new FileInputStream(xmlFile);
                        // parse the file
                        CompilationUnit cu = JavaParser.parse(in);
                        cu.accept(new MethodVisitor(), null);
                    }catch (Exception e){
                        // ignore
                    }finally {
                        if(in != null){
                            try{
                                in.close();
                            }catch(Exception e){
                                // ignore
                            }
                        }
                    }
                }
            }
        }

        return total;
    }

    @Qualifier
    private void pri(){}

    @Qualifier
    protected void pro(){}

    @Qualifier
    public void pub(){}

    private static final Set<String> ignoreAnnotationNameSet = Sets.newHashSet("SuppressWarnings", "Deprecated");


    private static class MethodVisitor extends VoidVisitorAdapter<Void> {

        private String packageName;
        private String className;

        @Override
        public void visit(MethodDeclaration n, Void arg) {
//            System.out.println("method:"+n.getName());
//            System.out.println("annotation:"+n.getAnnotations());
//            System.out.println("private:"+n.getModifiers());

            boolean isPri = (n.getModifiers() & 2) == 2;
            if(isPri && !CollectionsUtil.isNullOrEmpty(n.getAnnotations())){
                boolean hasNotAllowAnnotation = false;
                for (AnnotationExpr annotationExpr : n.getAnnotations()) {
                    if(!ignoreAnnotationNameSet.contains(annotationExpr.getName().getName())){
                        hasNotAllowAnnotation = true;
                        break;
                    }
                }

                if(hasNotAllowAnnotation){
                    System.out.println("-----------private方法加注解-----------");
                    System.out.println("路径：" + packageName + "." + className);
                    System.out.println("方法名：" + n.getName());
                    System.out.println("注解名称：" + n.getAnnotations());
                }
            }
            super.visit(n, arg);
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
//            System.out.println("class:"+n.getName());
//            System.out.println("extends:"+n.getExtends());
//            System.out.println("implements:"+n.getImplements());
            className = n.getName();

//            if(!n.isInterface() && n.getName().contains("Manager")){
//                if(CollectionsUtil.isNullOrEmpty(n.getAnnotations())){
//                    System.out.println("-----------Manager上无注解-----------");
//                    System.out.println("路径：" + packageName + "." + className);
//                    System.out.println("类名：" + n.getName());
//                    System.out.println("注解名称：" + n.getAnnotations());
//                }
//            }

            super.visit(n, arg);
        }

        @Override
        public void visit(PackageDeclaration n, Void arg) {
//            System.out.println("package:"+n.getName());
            packageName = n.getName().getName();
            super.visit(n, arg);
        }
    }
}
