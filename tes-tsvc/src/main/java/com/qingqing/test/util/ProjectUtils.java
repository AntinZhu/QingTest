package com.qingqing.test.util;

import com.google.common.collect.Lists;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.bean.test.BankValidateResult;
import com.tencentcloudapi.faceid.v20180301.models.BankCardVerificationResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by zhujianxing on 2018/1/24.
 */
public class ProjectUtils {

    private static final String TEMPLATE_PATH = buildDirPath("tes-tsvc", "src", "main", "resources", "templates", "project");
    private static final String SP = File.separator.equals("\\")? "\\\\":File.separator;

    public static final String createEmptyProject(final String packagePath, final String svcName, final String poolCode, Map<String, Object> configMap){
        final String destProjectDir = "project" + File.separator + svcName + File.separator + svcName + "_" + System.currentTimeMillis();
        File tmpDir = new File(destProjectDir);
        mkDirNotExists(destProjectDir);

        String rootDitPah = tmpDir.getAbsolutePath();
        // 1. 创建目录
        // 1.1 生成java相关目录
        mkJava(rootDitPah, packagePath);
        // 1.2 生成resources下相关目录
        mkResources(rootDitPah);
        // 1.3 生成其他
        mkOthers(rootDitPah);
        // 2. 生成文件

        // 2.1 构建生成文件所需数据
        final Map<String, Object> dataMap = buildTemplateData(packagePath, svcName, poolCode, configMap);
//         2.2 生成pom文件
//        generatePom(destProjectDir, dataMap);
//        generateOthers(destProjectDir, dataMap);

        File dir = new File(TEMPLATE_PATH);
        try {
            DirSearchUtils.checkDir(dir, new DirSearchUtils.FileHandler() {
                @Override
                public void handle(File file) throws IOException {
                    if(file.getName().endsWith(".ftl")){
                        String fileFullPath = file.getAbsolutePath();
                        String fileAbsPath = fileFullPath.substring(fileFullPath.indexOf(TEMPLATE_PATH ) + TEMPLATE_PATH.length() + 1);
                        String destFilePath = fileAbsPath.substring(0, fileAbsPath.length() - 4);
                        String javaPath = buildDirPath("src", "main", "java");
                        destFilePath = destFilePath.replace(javaPath, buildDirPath(javaPath, packagePath));

                        String fileName = file.getName();
                        String fileDir;
                        if(fileAbsPath.equals(fileName)){
                            fileDir = "";
                        }else{
                            fileDir = fileAbsPath.substring(0, fileAbsPath.length() - fileName.length() - 1);
                        }

                        System.out.println("fileAbsPath:" + fileAbsPath);
                        System.out.println("fileName:" + fileName);
                        System.out.println("fileDir:" + fileDir);


                        generateFile(buildDirPath(TEMPLATE_PATH, fileDir), fileName, dataMap, buildFilePath(destProjectDir, destFilePath));
                    }
                }
            });
        } catch (IOException e) {
            throw new QingQingRuntimeException("generate file error", e);
        }
        // TODO zjx

        // 返回生成项目的根目录
        return destProjectDir;
    }

    private static final void generatePom(String destProjectDir, Map<String, Object> dataMap){
        // 生成parent的pom.xml
        generateFile(TEMPLATE_PATH, "pom.xml.ftl", dataMap, buildFilePath(destProjectDir, "pom.xml"));

        // 生成parent的pom.xml
        generateFile(buildDirPath(TEMPLATE_PATH, "svc"), "pom.xml.ftl", dataMap, buildFilePath(destProjectDir, "svc", "pom.xml"));
    }

    private static final void generateOthers(String destProjectDir, Map<String, Object> dataMap){
        // 生成web.xml
        generateFile(buildDirPath(TEMPLATE_PATH, "svc.src.main.webapp.WEB-INF"), "web.ftl", dataMap, buildFilePath(destProjectDir, "svc.src.main.webapp.WEB-INF", "web.xml"));
    }

    private static final String buildDirPath(String rootDir, String ... pathNames){
        String resultPath = rootDir;
        for (String pathName : pathNames) {
            resultPath += File.separator + pathName.replaceAll("\\.", SP);
        }

        return resultPath;
    }

    private static final String buildFilePath(String rootDir, String ... pathNames){
        String resultPath = rootDir;
        int i = 1;
        for (String pathName : pathNames) {
            String filePath = (i == pathNames.length)? pathName : pathName.replaceAll("\\.", SP);
            resultPath += File.separator + filePath;
            i++;
        }

        return resultPath;
    }

    private static final void mkResources(String rootDir){
        String resourceDir = rootDir + (".svc.src.main.resources").replaceAll("\\.", SP);
        batchMkDir(resourceDir, Lists.newArrayList(
                "dev",
                "local",
                "mybatis",
                "prd",
                "tst"
                )
        );
    }

    private static final void mkOthers(String rootDir){
        String webDir = rootDir + (".svc.src.main.webapp.WEB-INF").replaceAll("\\.", SP);
        mkDirNotExists(webDir);
    }

    private static final void mkJava(String rootDir, String packagePath){
        String javaDir = rootDir + (".svc.src.main.java." + packagePath).replaceAll("\\.", SP);
        batchMkDir(javaDir, Lists.newArrayList(
                "bean",
                "config",
                "manager",
                "service",
                "dao.mybatis",
                "utils",
                "xinterface.controller.v1",
                "xinterface.converter",
                "xinterface.errorcode",
                "domain"
                )
        );
    }

    private static final void batchMkDir(String rootDir, List<String> subDirs){
        if(CollectionsUtil.isNullOrEmpty(subDirs)){
            return;
        }

        for (String subDir : subDirs) {
            mkDirNotExists(rootDir + File.separator + subDir.replaceAll("\\.", SP));
        }
    }

    public static final void mkDirNotExists(String path){
        File tmpDir = new File(path);
        if(!tmpDir.exists()){
            boolean tmpMkResult = tmpDir.mkdirs();
            if(!tmpMkResult){
                throw new QingQingRuntimeException("mk dir fail");
            }
        }
    }

    private static Map<String, Object> buildTemplateData(String packagePath, String svcName, String poolCode, Map<String, Object> configMap){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("artifactId", QingStringUtil.humpToOtherStyle(svcName, "-"));
        dataMap.put("svcName", QingStringUtil.humpToOtherStyle(svcName, ""));
        dataMap.put("basePackage", packagePath);
        dataMap.put("poolCode", poolCode);
        dataMap.put("date", TimeUtil.dateToString(new Date(), "yyyy/MM/DD"));

        // 通用jar包版本走配置
       dataMap.putAll(configMap);
        return dataMap;
    }

    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("idencode_util_version", "1.3.0-SNAPSHOT");
        configMap.put("qingqing_starter_version", "1.1.6-SNAPSHOT");
        configMap.put("api_common_version", "1.9.2.8-SNAPSHOT");
        configMap.put("spring_web_util_version", "1.4.8.7-SNAPSHOT");

        ProjectUtils.createEmptyProject("com.qingqing.api.teacher", "teacherSvc", "ps-teacher-svc", configMap);
    }

    public static void generateFile(String templateDir, String templateFileName, Map<String, Object> dataMap, String outputFilePath) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(templateDir));
            // step4 加载模版文件
            Template template = configuration.getTemplate(templateFileName);
            // step5 生成数据
            File docFile = new File(outputFilePath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
        } catch (Exception e) {
            throw new QingQingRuntimeException("generate file fail, templateFileName:" + templateFileName + ", outputFilePath:" + outputFilePath, e);
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
