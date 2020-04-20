package com.qingqing.test.util;

import com.google.common.collect.Lists;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.manager.project.customhanlde.IProjectCustomHandler;
import com.qingqing.test.manager.project.customhanlde.impl.CommonMockProjectCustomHandler;
import com.qingqing.test.manager.project.customhanlde.impl.MySQLProjectCustomHandler;
import com.qingqing.test.manager.project.customhanlde.impl.SelfRedisProjectCustomHandler;
import com.qingqing.test.manager.project.customhanlde.impl.UserInfoDpProjectCustomHandler;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zhujianxing on 2018/1/24.
 */
public class QingProjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(QingProjectUtils.class);

    private static final boolean isWins = File.separator.equals("\\");
    public static final String TEMPLATE_PATH = buildDirPath("src", "main", "resources", "templates", "project");
    public static final String TEMPLATE_PATH_COMMON = buildDirPath(TEMPLATE_PATH, "common");
    public static final String TEMPLATE_PATH_CUSTOM = buildDirPath(TEMPLATE_PATH, "custom");
    private static final String TMP_DIR = isWins? "D:\\" : "/home/qingqing/";

    private static final String SP = File.separator.equals("\\")? "\\\\":File.separator;

    public static final String createEmptyProject(final ProjectCustomBean projectCustomBean, List<IProjectCustomHandler> customHandlerList){
        String svcName = projectCustomBean.getSvcName();
        final String destProjectDir = TMP_DIR + "project" + File.separator + svcName + File.separator + svcName + "_" + System.currentTimeMillis();
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
        buildCommonFile(destProjectDir, projectCustomBean);

        // 返回生成项目的根目录
        return destProjectDir;
    }

    private static final void buildCommonFile(final String destProjectDir, final ProjectCustomBean projectCustomBean){
        // 构建生成文件所需数据
        final Map<String, Object> dataMap = buildTemplateData(projectCustomBean);

        String filePath = QingProjectUtils.class.getResource("QingProjectUtils.class").getPath();
        logger.info("QingProjectUtils.class filePath:" + filePath);
        if(filePath.contains(".jar!")){
            // 以jar包运行
            String jarPath = QingProjectUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile();

            JarFile jarFile = null;
            try {
                jarPath = java.net.URLDecoder.decode(jarPath, "UTF-8");
                int firstIdx = jarPath.indexOf("!");
                if(firstIdx != -1){
                    jarPath = jarPath.substring(0, firstIdx);
                }
                logger.info("jarPath:" + jarPath);

                jarFile = new JarFile(new File(jarPath.substring(isWins? 6 : 5)));
                Enumeration<JarEntry> es = jarFile.entries();
                final String commonTemplatePath = "classes/templates/project/common/";
                while (es.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) es.nextElement();
                    String nameEntryName = new String(jarEntry.getName().getBytes(), "UTF-8");
                    if(nameEntryName.contains(commonTemplatePath) && nameEntryName.endsWith(".ftl")){
                        logger.info("nameEntryName:" + nameEntryName);
                        // 路径拼接
                        URL url = new URL("jar:" + jarPath + "!/" + nameEntryName);
                        // 标准输入流
                        InputStream in = url.openStream();
                        byte[] buf = new byte[in.available()];
                        in.read(buf);
                        String templateValue = new String(buf, "utf-8");
                        logger.info("templateValue:" + templateValue);

                        // 去除.ftl
                        String destFilePath = nameEntryName.substring(0, nameEntryName.length() - 4);
                        // 去除不需要的前缀
                        destFilePath = destFilePath.substring(destFilePath.indexOf(commonTemplatePath) + commonTemplatePath.length());
                        // java目录下文件路径增加basePckage
                        destFilePath = destFilePath.replace("src/main/java", buildDirPath("src.main.java", projectCustomBean.getBasePackage()));
                        // jar包文件分隔符装换为系统的文件分隔符
//                        destFilePath = destFilePath.replaceAll("/", File.separator);
                        logger.info("destFilePath:" + destFilePath);
                        generateFileWithStringTemplate(nameEntryName, templateValue, dataMap, buildFilePath(destProjectDir , destFilePath));
                        in.close();
                    }
                }
            } catch (Exception e) {
                throw new QingQingRuntimeException("generate file in jar error", e);
            }
        }else{
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

                            generateFile(buildDirPath(TEMPLATE_PATH_COMMON, fileDir), fileName, dataMap, buildFilePath(destProjectDir, destFilePath));
                        }
                    }

                    @Override
                    public void doAfterFileChecked(File dir) {}
                });
            } catch (IOException e) {
                throw new QingQingRuntimeException("generate file error", e);
            }
        }
    }

    public static final String buildDirPath(String rootDir, String ... pathNames){
        String resultPath = rootDir.replaceAll("\\.", SP);
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

        QingProjectUtils.createEmptyProject(projectCustomBean, handlerList);
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
            if (!docFile.getParentFile().exists()) {
                docFile.getParentFile().mkdirs();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            out.flush();
        } catch (Exception e) {
            throw new QingQingRuntimeException("generate file fail, templateFileName:" + templateFileName + ", outputFilePath:" + outputFilePath, e);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void generateFileWithStringTemplate(String path, String templateValue, Map<String, Object> dataMap, String outputFilePath) {
        Configuration config  = new Configuration();
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate(path, templateValue);
        config.setTemplateLoader(stringTemplateLoader);

        Template template = null;
        Writer out = null;
        try {
            template = config.getTemplate(path,"UTF-8");
            out = new BufferedWriter(new FileWriter(outputFilePath));
            template.process(dataMap, out);
            out.flush();
        } catch (Exception e) {
            throw new QingQingRuntimeException("generate file fail, templateFileName:" + path + ", outputFilePath:" + outputFilePath, e);
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
