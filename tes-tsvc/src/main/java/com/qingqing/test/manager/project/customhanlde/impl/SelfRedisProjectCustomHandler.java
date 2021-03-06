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
public class SelfRedisProjectCustomHandler extends AbstractProjectCustomHandler {

    private static final List<ProjectCustomTemplate> TEMPLATE_LIST = Lists.newArrayList(
        new ProjectCustomTemplate("custom.config.redis", "SelfRedisConfig.java.ftl", "svc.src.main.java.{basePackage}.config.redis")
    );

    @Override
    protected ProjectCustomConfigType getSupportProjectCustomConfigType() {
        return ProjectCustomConfigType.REDIS_SELF;
    }

    @Override
    protected List<ProjectCustomTemplate> getTemplateFileList() {
        return TEMPLATE_LIST;
    }

    @Override
    protected Map<String, Object> generateCustomData(ProjectCustomBean projectCustomBean, ProjectCustomItem projectCustomItem) {
        return buildBaseDataMap(projectCustomBean);
    }

    @Override
    protected void doAfterHandleSucc(ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean) {
        // 标记系统引入userinfosp，后续处理中会引入userinfodp对应的jar包
        projectCustomBean.addCustomMapping("use_selfRedis", 1);
    }
}
