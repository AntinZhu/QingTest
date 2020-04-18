package com.qingqing.test.manager.project.impl;

import com.google.common.collect.Lists;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.bean.project.ProjectCustomTemplate;
import com.qingqing.test.manager.project.AbstractProjectCustomHandler;
import com.qingqing.test.manager.project.IProjectCustomHandler;
import com.qingqing.test.util.ProjectUtils;
import com.qingqing.test.util.QingStringUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Component
public class MySQLProjectCustomHandler extends AbstractProjectCustomHandler {

    private static final List<ProjectCustomTemplate> TEMPLATE_LIST = Lists.newArrayList(
        new ProjectCustomTemplate("mysql", "DataSource{dbName}Config.java.ftl", "svc.src.main.java.{basePackage}.config.db")
    );

    @Override
    protected ProjectCustomConfigType getSupportProjectCustomConfigType() {
        return ProjectCustomConfigType.MYSQL_DB;
    }

    @Override
    protected List<ProjectCustomTemplate> getTemplateFileList() {
        return TEMPLATE_LIST;
    }

    @Override
    protected Map<String, Object> generateCustomData(ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem) {
        Map<String, Object> dataMap = new HashMap<>();
        String dbName = getDbName(projectCustomItem);
        String simpleDbName = getSimpleDbName(dbName);
        dataMap.put("dbName", dbName);
        dataMap.put("simpleDbName", simpleDbName);
        dataMap.put("basePackage", projectCustomBean.getBasePackage());
        dataMap.put("basePackagePath", projectCustomBean.getBasePackage().replaceAll("\\.", "/"));
        dataMap.put("upDbName", QingStringUtil.upperCase(simpleDbName));

        return dataMap;
    }

    @Override
    protected String customDestFileName(ProjectCustomTemplate customTemplate, ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem) {
        String destFileName = customTemplate.getTemplateFile().replaceAll("\\{dbName\\}", QingStringUtil.upperCase(getSimpleDbName(getDbName(projectCustomItem))));

        return buildJavaFile(projectCustomBean.getBasePackage(), customTemplate.getDestDir(), destFileName);
    }

    private String getDbName(ProjectCustomItem projectCustomItem){
        Map<String, String> customDataMap = JsonUtil.parserJsonMap(projectCustomItem.getCustomJson(), String.class);
        String dbName = customDataMap.get("dbName");
        if(dbName == null){
            dbName = "";
        }

        return dbName;
    }

    private String getSimpleDbName(String dbName){
       String simpleDbName = dbName;
        if(simpleDbName.startsWith("qq_")){
            simpleDbName = simpleDbName.substring(3);
       }

        simpleDbName = simpleDbName.replaceAll("_", "");
        return simpleDbName;
    }
}
