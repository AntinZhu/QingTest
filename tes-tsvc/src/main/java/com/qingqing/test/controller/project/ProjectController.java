package com.qingqing.test.controller.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.StringUtils;
import com.qingqing.test.aspect.validate.IpLoginValid;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.IdAndBoolBean;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.project.ProjectCustomBean;
import com.qingqing.test.bean.project.ProjectCustomItem;
import com.qingqing.test.controller.DownloadController;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.controller.project.errorCode.ProjectGenerateErrorCode;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.domain.mock.MockType;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable;
import com.qingqing.test.manager.mock.rule.MockRuleManager;
import com.qingqing.test.manager.project.ProjectGeneratorManager;
import com.qingqing.test.service.mock.MockRuleService;
import com.qingqing.test.service.mock.MockTypeService;
import com.qingqing.test.util.QingDirUtils;
import com.qingqing.test.util.QingMapUtils;
import com.qingqing.test.util.ZipUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Controller
@RequestMapping("/v1/project")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectGeneratorManager projectGeneratorManager;

    @RequestMapping(value = "page")
    public String page(){
        return "project/create_project";
    }

    @RequestMapping(value = "create")
    @ResponseBody
    public void create(@RequestParam Map<String, Object> params, HttpServletResponse response){
        String requestParam = new JSONObject(params).toJSONString();
        logger.info("create project param:" + requestParam);
        ProjectCustomBean projectCustomBean = JSON.parseObject(requestParam, ProjectCustomBean.class);
        projectCustomBean.setCustomItemList(JsonUtil.parserJsonList((String)params.get("custom"), ProjectCustomItem.class));
        logger.info("custom:" + params.get("custom"));

        //将文件进行打包下载
        String destFilePath = null;
        OutputStream out = null; //输出流
        try {
            destFilePath = projectGeneratorManager.buildProject(projectCustomBean);
            String downloadName = projectCustomBean.getSvcName() + ".zip";

            out = response.getOutputStream();
            byte[] data = ZipUtils.createZip(destFilePath);//服务器存储地址
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + downloadName);
            out.write(data);
            out.flush();
        } catch (Exception e) {
            throw new ErrorCodeException(ProjectGenerateErrorCode.fail, "generate project fail", e);
        }finally {
            if(destFilePath != null){
                try{
                    QingDirUtils.deleteDir(destFilePath);
                }catch (Exception e){
                    logger.error("delete project fail, svcName:" + projectCustomBean.getSvcName(), e);
                }
            }
        }
    }
}
