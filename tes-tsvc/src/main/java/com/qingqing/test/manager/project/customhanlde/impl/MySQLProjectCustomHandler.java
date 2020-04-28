package com.qingqing.test.manager.project.customhanlde.impl;

import com.google.common.collect.Lists;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.bean.project.ProjectCustomTemplate;
import com.qingqing.test.manager.project.customhanlde.AbstractProjectCustomHandler;
import com.qingqing.test.util.QingStringUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Component
public class MySQLProjectCustomHandler extends AbstractProjectCustomHandler {

    private static final List<ProjectCustomTemplate> TEMPLATE_LIST = Lists.newArrayList(
        new ProjectCustomTemplate("custom.config.mysql", "DataSource{dbName}{type}Config.java.ftl", "svc.src.main.java.{basePackage}.config.db")
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
        Map<String, Object> dataMap = buildBaseDataMap(projectCustomBean);

        CustomParam customParam = JsonUtil.getObjectFromJson(projectCustomItem.getCustomJson(), CustomParam.class);
        String dbName = customParam.getDbName();
        String simpleDbName = getSimpleDbName(dbName);
        dataMap.put("dbName", dbName);
        dataMap.put("simpleDbName", simpleDbName);
        dataMap.put("basePackagePath", projectCustomBean.getBasePackage().replaceAll("\\.", "/"));
        dataMap.put("type", customParam.getType());

        return dataMap;
    }

    @Override
    protected String customDestFileName(ProjectCustomTemplate customTemplate, ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem) {
        CustomParam customParam = JsonUtil.getObjectFromJson(projectCustomItem.getCustomJson(), CustomParam.class);

        String destFileName = customTemplate.getTemplateFile().replaceAll("\\{dbName\\}", QingStringUtil.upperCase(getSimpleDbName(customParam.getDbName())));
        destFileName = destFileName.replaceAll("\\{type\\}", QingStringUtil.upperCase(customParam.getType()));

        return buildJavaFile(projectCustomBean.getBasePackage(), customTemplate.getDestDir(), destFileName);
    }

    @Override
    protected void doAfterHandleSucc(ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean) {
        CustomParam customParam = JsonUtil.getObjectFromJson(projectCustomItem.getCustomJson(), CustomParam.class);
        String dbName = customParam.getDbName();
        String simpleDbName = getSimpleDbName(dbName);

        projectCustomBean.addCustomDir("svc.src.main.java.{basePackage}.dao.mybatis." + simpleDbName);
    }

    private String getSimpleDbName(String dbName){
       String simpleDbName = dbName;
        if(simpleDbName.startsWith("qq_")){
            simpleDbName = simpleDbName.substring(3);
       }

        simpleDbName = simpleDbName.replaceAll("_", "");
        return simpleDbName;
    }

    public static class CustomParam{
        private String dbName;
        private String type = "master";

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
