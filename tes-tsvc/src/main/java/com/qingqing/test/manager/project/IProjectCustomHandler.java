package com.qingqing.test.manager.project;

import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomConfigType;
import com.qingqing.test.bean.project.ProjectCustomItem;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public interface IProjectCustomHandler {

    boolean supports(ProjectCustomItem projectCustomItem);

    void handle(ProjectCustomItem projectCustomItem, ProjectCustomBean projectCustomBean);
}
