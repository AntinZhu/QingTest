package com.qingqing.test.manager.project;

import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.bean.project.ProjectCustomTemplate;
import com.qingqing.test.manager.project.IProjectCustomHandler;
import com.qingqing.test.util.ProjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public abstract class AbstractProjectCustomHandler implements IProjectCustomHandler{

    @Override
    public boolean supports(ProjectCustomItem projectCustomItem) {
        return getSupportProjectCustomConfigType().equals(projectCustomItem.getProjectCustomConfigType());
    }

    public void handle(ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean){
        List<ProjectCustomTemplate> templateList = getTemplateFileList();
        if(templateList != null){
            Map<String, Object> customTemplateData = generateCustomData(projectCustomBean, projectCustomItem);

            for (ProjectCustomTemplate projectCustomTemplate : templateList) {
                String templateFileDir = ProjectUtils.buildDirPath(ProjectUtils.TEMPLATE_PATH_CUSTOM, projectCustomTemplate.getTemplateDir());
                String destFilePath = ProjectUtils.buildFilePath(projectCustomBean.getDestProjectDir(), customDestFileName(projectCustomTemplate, projectCustomBean, projectCustomItem));
                ProjectUtils.generateFile(templateFileDir, projectCustomTemplate.getTemplateFile(), customTemplateData, destFilePath);
            }
        }
    }

    protected abstract List<ProjectCustomTemplate> getTemplateFileList();

    protected abstract Map<String, Object> generateCustomData(ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem);

    protected abstract String customDestFileName(ProjectCustomTemplate customTemplate, ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem);

    protected String buildJavaFile(String basePackage, String destFileDir, String destFileName){
        return ProjectUtils.buildFilePath(destFileDir.replaceAll("\\{basePackage\\}", basePackage), destFileName);
    }

    protected abstract ProjectCustomConfigType getSupportProjectCustomConfigType();
}
