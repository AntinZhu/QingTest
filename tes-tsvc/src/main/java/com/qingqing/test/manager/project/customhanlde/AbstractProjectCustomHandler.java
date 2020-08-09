package com.qingqing.test.manager.project.customhanlde;

import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.bean.project.ProjectCustomTemplate;
import com.qingqing.test.util.QingProjectUtils;
import freemarker.template.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public abstract class AbstractProjectCustomHandler implements IProjectCustomHandler{

    @Override
    public boolean supports(ProjectCustomItem projectCustomItem) {
        return getSupportProjectCustomConfigType().equals(projectCustomItem.getItemType());
    }

    public void handle(Configuration stringTemplateConfig, ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean){
        List<ProjectCustomTemplate> templateList = getTemplateFileList();
        if(templateList != null){
            Map<String, Object> customTemplateData = generateCustomData(projectCustomBean, projectCustomItem);

            for (ProjectCustomTemplate projectCustomTemplate : templateList) {
                String templateFileDir = QingProjectUtils.buildDirPath(projectCustomTemplate.getTemplateDir());
                String destFilePath = QingProjectUtils.buildFilePath(projectCustomBean.getDestProjectDir(), customDestFileName(projectCustomTemplate, projectCustomBean, projectCustomItem));
                QingProjectUtils.generateFile(stringTemplateConfig, templateFileDir, projectCustomTemplate.getTemplateFile(), customTemplateData, destFilePath);
            }
        }

        doAfterHandleSucc(projectCustomItem, projectCustomBean);
    }

    protected void doAfterHandleSucc(ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean){}

    protected abstract List<ProjectCustomTemplate> getTemplateFileList();

    protected abstract Map<String, Object> generateCustomData(ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem);

    protected String customDestFileName(ProjectCustomTemplate customTemplate, ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem){
        return buildJavaFile(projectCustomBean.getBasePackage(), customTemplate.getDestDir(), customTemplate.getTemplateFile());
    }

    protected String buildJavaFile(String basePackage, String destFileDir, String destFileName){
        String javaFileName = destFileName;
        if(javaFileName.endsWith(QingProjectUtils.TEMPLATE_FILE_SUFFIX)){
            javaFileName = javaFileName.substring(0,javaFileName.length() - QingProjectUtils.TEMPLATE_FILE_SUFFIX.length());
        }
        return QingProjectUtils.buildFilePath(destFileDir.replaceAll("\\{basePackage\\}", basePackage), javaFileName);
    }

    protected abstract ProjectCustomConfigType getSupportProjectCustomConfigType();

    protected Map<String, Object> buildBaseDataMap(ProjectCustomBean projectCustomBean){
        Map<String, Object> baseDataMap = new HashMap<>();
        baseDataMap.put("basePackage", projectCustomBean.getBasePackage());
        baseDataMap.put("svcName", projectCustomBean.getSvcName().toLowerCase());
        baseDataMap.put("poolCode", projectCustomBean.getPoolCode());
        baseDataMap.put("date", TimeUtil.dateToString(new Date(), "yyyy/MM/dd"));
        if(projectCustomBean.getCreateUser() != null){
            baseDataMap.put("user", projectCustomBean.getCreateUser());
        }

        return baseDataMap;
    }
}
