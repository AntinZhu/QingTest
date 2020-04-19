package com.qingqing.test.util;

import com.google.common.collect.Lists;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.bean.test.BankValidateResult;
import com.qingqing.test.manager.project.IProjectCustomHandler;
import com.qingqing.test.manager.project.impl.CommonMockProjectCustomHandler;
import com.qingqing.test.manager.project.impl.MySQLProjectCustomHandler;
import com.qingqing.test.manager.project.impl.SelfRedisProjectCustomHandler;
import com.qingqing.test.manager.project.impl.UserInfoDpProjectCustomHandler;
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

    public static final String TEMPLATE_PATH = buildDirPath("tes-tsvc", "src", "main", "resources", "templates", "project");
    public static final String TEMPLATE_PATH_COMMON = buildDirPath(TEMPLATE_PATH, "common");
    public static final String TEMPLATE_PATH_CUSTOM = buildDirPath(TEMPLATE_PATH, "custom");

    private static final String SP = File.separator.equals("\\")? "\\\\":File.separator;

    public static final String createEmptyProject(final ProjectCustomBean projectCustomBean, List<IProjectCustomHandler> customHandlerList){
        String svcName = projectCustomBean.getSvcName();
        final String destProjectDir = "project" + File.separator + svcName + File.separator + svcName + "_" + System.currentTimeMillis();
        projectCustomBean.setDestProjectDir(destProjectDir);

        File tmpDir = new File(destProjectDir);
        mkDirNotExists(destProjectDir);

        String rootDitPah = tmpDir.getAbsolutePath();
        // 1. 创建目录
        // 1.1 生成java相关目录
        mkJava(rootDitPah, projectCustomBean.getBasePackage());
        // 1.2 生成resources下相关目录
        mkResources(rootDitPah);
        // 1.3 生成其他
        mkOthers(rootDitPah);
        // 2. 生成文件
        // 2.1 生成定制化文件：DB配置，ZK配置，mongoDB，对外异步事件，对内异步事件
        if(!CollectionsUtil.isNullOrEmpty(projectCustomBean.getCustomItemList())){
            for (ProjectCustomItem projectCustomItem : projectCustomBean.getCustomItemList()) {
                for (IProjectCustomHandler iProjectCustomHandler : customHandlerList) {
                    if(iProjectCustomHandler.supports(projectCustomItem)){
                        iProjectCustomHandler.handle(projectCustomItem, projectCustomBean);
                    }
                }
            }
        }

        // 2.2 生成必备文件
        // 构建生成文件所需数据
        final Map<String, Object> dataMap = buildTemplateData(projectCustomBean);
        File dir = new File(TEMPLATE_PATH_COMMON);
        try {
            DirSearchUtils.checkDir(dir, new DirSearchUtils.FileHandler() {
                @Override
                public void handle(File file) throws IOException {
                    if(file.getName().endsWith(".ftl")){
                        String fileFullPath = file.getAbsolutePath();
                        String fileAbsPath = fileFullPath.substring(fileFullPath.indexOf(TEMPLATE_PATH_COMMON ) + TEMPLATE_PATH_COMMON.length() + 1);
                        String destFilePath = fileAbsPath.substring(0, fileAbsPath.length() - 4);
                        String javaPath = buildDirPath("src", "main", "java");

                        destFilePath = destFilePath.replace(javaPath, buildDirPath(javaPath, projectCustomBean.getBasePackage()));

                        String fileName = file.getName();
                        String fileDir;
                        if(fileAbsPath.equals(fileName)){
                            fileDir = "";
                        }else{
                            fileDir = fileAbsPath.substring(0, fileAbsPath.length() - fileName.length() - 1);
                        }

//                        System.out.println("fileAbsPath:" + fileAbsPath);
//                        System.out.println("fileName:" + fileName);
//                        System.out.println("fileDir:" + fileDir);


                        generateFile(buildDirPath(TEMPLATE_PATH_COMMON, fileDir), fileName, dataMap, buildFilePath(destProjectDir, destFilePath));
                    }
                }
            });
        } catch (IOException e) {
            throw new QingQingRuntimeException("generate file error", e);
        }

        // 返回生成项目的根目录
        return destProjectDir;
    }

    public static final String buildDirPath(String rootDir, String ... pathNames){
        String resultPath = rootDir;
        for (String pathName : pathNames) {
            resultPath += File.separator + pathName.replaceAll("\\.", SP);
        }

        return resultPath;
    }

    public static final String buildFilePath(String rootDir, String ... pathNames){
        String resultPath = rootDir.replaceAll("\\.", SP);
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

    private static Map<String, Object> buildTemplateData(ProjectCustomBean projectCustomBean){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("artifactId", QingStringUtil.humpToOtherStyle(projectCustomBean.getSvcName(), "-"));
        dataMap.put("svcName", QingStringUtil.humpToOtherStyle(projectCustomBean.getSvcName(), ""));
        dataMap.put("basePackage", projectCustomBean.getBasePackage());
        dataMap.put("poolCode", projectCustomBean.getPoolCode());
        dataMap.put("date", TimeUtil.dateToString(new Date(), "yyyy/MM/dd"));
        if(projectCustomBean.getCreateUser() != null){
            dataMap.put("user", projectCustomBean.getCreateUser());
        }

        // 通用jar包版本走配置
        if(!CollectionsUtil.isNullOrEmpty(projectCustomBean.getJarVersionMap())){
            for (Entry<String, Object> stringObjectEntry : projectCustomBean.getJarVersionMap().entrySet()) {
                dataMap.put(stringObjectEntry.getKey().replaceAll("-", "_"), stringObjectEntry.getValue());
            }
        }

        // 定制属性
        if(!CollectionsUtil.isNullOrEmpty(projectCustomBean.getCustomMap())){
            dataMap.putAll(projectCustomBean.getCustomMap());
        }
        return dataMap;
    }

    public static void main(String[] args) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("idencode_util_version", "1.3.0-SNAPSHOT");
        configMap.put("qingqing_starter_version", "1.1.6-SNAPSHOT");
        configMap.put("api_common_version", "1.9.2.8-SNAPSHOT");
        configMap.put("spring_web_util_version", "1.4.8.7-SNAPSHOT");

        List<ProjectCustomItem> customItemList = Lists.newArrayList(
                new ProjectCustomItem("{\"dbName\":\"qq_data\"}", ProjectCustomConfigType.MYSQL_DB),
                new ProjectCustomItem(null, ProjectCustomConfigType.REDIS_SELF),
                new ProjectCustomItem(null, ProjectCustomConfigType.REDIS_USER_INFO_DP),
                new ProjectCustomItem(null, ProjectCustomConfigType.COMMON_MOCK)
        );

        ProjectCustomBean projectCustomBean = new ProjectCustomBean();
        projectCustomBean.setBasePackage("com.qingqing.api.teacher");
        projectCustomBean.setCreateUser("zhujianxing");
        projectCustomBean.setJarVersionMap(configMap);
        projectCustomBean.setSvcName("teacherSvc");
        projectCustomBean.setPoolCode("ps-teacher-svc");
        projectCustomBean.setCustomItemList(customItemList);

        MySQLProjectCustomHandler mySQLProjectCustomHandler = new MySQLProjectCustomHandler();
        SelfRedisProjectCustomHandler selfRedisProjectCustomHandler = new SelfRedisProjectCustomHandler();
        UserInfoDpProjectCustomHandler userInfoDpProjectCustomHandler = new UserInfoDpProjectCustomHandler();
        CommonMockProjectCustomHandler commonMockProjectCustomHandler = new CommonMockProjectCustomHandler();
        List<IProjectCustomHandler> handlerList = Lists.newArrayList();
        handlerList.add(mySQLProjectCustomHandler);
        handlerList.add(selfRedisProjectCustomHandler);
        handlerList.add(userInfoDpProjectCustomHandler);
        handlerList.add(commonMockProjectCustomHandler);

        ProjectUtils.createEmptyProject(projectCustomBean, handlerList);
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
            if(!docFile.getParentFile().exists()){
                docFile.getParentFile().mkdirs();
            }
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
