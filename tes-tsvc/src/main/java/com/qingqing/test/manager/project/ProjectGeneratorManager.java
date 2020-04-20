package com.qingqing.test.manager.project;

import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.manager.config.TestConfigManager;
import com.qingqing.test.manager.project.customhanlde.IProjectCustomHandler;
import com.qingqing.test.util.QingProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2020/4/20.
 */
@Component
public class ProjectGeneratorManager {

    @Autowired
    private TestConfigManager testConfigManager;
    @Autowired
    private List<IProjectCustomHandler> projectCustomHandlerList;

    public String buildProject(ProjectCustomBean projectCustomBean){
        projectCustomBean.setJarVersionMap(testConfigManager.getJarVersionMap());

        return QingProjectUtils.createEmptyProject(projectCustomBean, projectCustomHandlerList);
    }
}
