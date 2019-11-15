package com.qingqing.test.controller.config;

import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.test.aspect.validate.IpLoginValid;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.bean.config.SaveCommonConfigBean;
import com.qingqing.test.domain.config.TestCommonConfig;
import com.qingqing.test.manager.config.TestCommonConfigManager;
import com.qingqing.test.service.config.TestCommonConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/config/common")
public class CommonConfigController {
    private static final Logger logger = LoggerFactory.getLogger(CommonConfigController.class);

    @Autowired
    private TestCommonConfigService testCommonConfigService;
    @Autowired
    private TestCommonConfigManager testCommonConfigManager;

    @RequestMapping("add_page")
    @IpLoginValid
    public String add(@RequestParam(value = "id", defaultValue = "0") Long id, Model model){
        if(id > 0){
            model.addAttribute("configBean", testCommonConfigManager.getBeanById(id));
        }

        return "interface/editCommonConfig";
    }

    @RequestMapping("invoke_page")
    public String invoke(@RequestParam(value = "id", defaultValue = "0") Long id, @RequestParam(value = "configKey", defaultValue = "") String configKey, Model model){
        TestCommonConfig config;
        if(id > 0){
            config = testCommonConfigService.selectById(id);
        }else{
            config = testCommonConfigService.selectByConfigKey(configKey);
        }
        model.addAttribute("config", config);

        return "interface/common_config";
    }

    @RequestMapping("add")
    @ResponseBody
    @IpLoginValid
    public SimpleResponse add(@RequestBody SaveCommonConfigBean bean){
        testCommonConfigManager.saveCommonConfig(bean);

        return new SimpleResponse(BaseResponse.SUCC_RESP);
    }

    @RequestMapping("update")
    @ResponseBody
    @IpLoginValid
    public SimpleResponse update(@RequestBody TestCommonConfig testCommonConfig){
        testCommonConfigService.update(testCommonConfig);

        return new SimpleResponse(BaseResponse.SUCC_RESP);
    }

    @RequestMapping("find_by_id")
    @ResponseBody
    public SingleResponse<TestCommonConfig> select(@RequestBody SimpleLongRequest request){
        TestCommonConfig resultList = testCommonConfigService.selectById(request.getData());

        SingleResponse<TestCommonConfig> response = new SingleResponse<>();
        response.setResponse(BaseResponse.SUCC_RESP);
        response.setResultList(resultList);
        return response;
    }

    @RequestMapping("delete")
    @ResponseBody
    @IpLoginValid
    public SimpleResponse delete(@RequestBody SimpleLongRequest request){
        testCommonConfigService.deleteById(request.getData());

        return new SimpleResponse(BaseResponse.SUCC_RESP);
    }

}
