package com.qingqing.test.manager.project.customhanlde.impl;

import com.google.common.collect.Lists;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.bean.project.ProjectCustomTemplate;
import com.qingqing.test.manager.project.customhanlde.AbstractProjectCustomHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/5/28.
 */
@Component
public class CommonMockProjectCustomHandler extends AbstractProjectCustomHandler {

    private static final List<ProjectCustomTemplate> TEMPLATE_LIST = Lists.newArrayList(
//        new ProjectCustomTemplate("custom.java.mock", "QingMock.java.ftl", "svc.src.main.java.{basePackage}.aspect.mock"),
//        new ProjectCustomTemplate("custom.java.mock", "QingMockAspect.java.ftl", "svc.src.main.java.{basePackage}.aspect.mock"),
//        new ProjectCustomTemplate("custom.java.mock", "QingMockType.java.ftl", "svc.src.main.java.{basePackage}.aspect.mock")
    );

    @Override
    protected ProjectCustomConfigType getSupportProjectCustomConfigType() {
        return ProjectCustomConfigType.COMMON_MOCK;
    }

    @Override
    protected List<ProjectCustomTemplate> getTemplateFileList() {
        return TEMPLATE_LIST;
    }

    @Override
    protected void doAfterHandleSucc(ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean) {
        projectCustomBean.addCustomMapping("useCommonMock", 1);
    }

    @Override
    protected Map<String, Object> generateCustomData(ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem) {
        return buildBaseDataMap(projectCustomBean);
    }
}
