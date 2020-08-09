package com.qingqing.test.manager.project.customhanlde;

import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;
import freemarker.template.Configuration;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public interface IProjectCustomHandler {

    boolean supports(ProjectCustomItem projectCustomItem);

    void handle(Configuration stringTemplateConfig, ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean);
}
