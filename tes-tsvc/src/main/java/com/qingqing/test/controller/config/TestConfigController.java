package com.qingqing.test.controller.config;

import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.test.aspect.validate.IpLoginValid;
import com.qingqing.test.aspect.validate.IpLoginValidType;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.bean.config.SaveCommonConfigBean;
import com.qingqing.test.domain.config.TestCommonConfig;
import com.qingqing.test.domain.config.TestConfig;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable;
import com.qingqing.test.manager.config.TestCommonConfigManager;
import com.qingqing.test.service.config.TestCommonConfigService;
import com.qingqing.test.service.config.TestConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.ProxyGenerator;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/test/config")
public class TestConfigController {
    private static final Logger logger = LoggerFactory.getLogger(TestConfigController.class);

    @Autowired
    private TestConfigService testConfigService;
    @Autowired
    private CommonSyncManager commonSyncManager;

    @RequestMapping("test")
    @ResponseBody
    public ProtoBufResponse.SimpleLongDataResponse add(@RequestBody SimpleLongRequest request){
        return ProtoBufResponse.SimpleLongDataResponse.newBuilder().setResponse(ProtoRespGenerator.SUCC_BASE_RESP).setData(request.getData()).build();
    }

    @RequestMapping("list_page")
    @IpLoginValid
    public String add(Model model){
        model.addAttribute("configList", testConfigService.selectAll());

        return "config/test_config_list";
    }


    @RequestMapping("add_page")
    @IpLoginValid
    public String add(@RequestParam(value = "id", defaultValue = "0") Long id, Model model){
        if(id > 0){
            model.addAttribute("id", id);
            model.addAttribute("config", testConfigService.findById(id));
        }

        return "config/edit_test_config";
    }

    @RequestMapping("add")
    @ResponseBody
    @IpLoginValid(validaType = IpLoginValidType.assign)
    public SimpleResponse add(@RequestBody TestConfig testConfig){
        testConfigService.add(testConfig);
        commonSyncManager.sync(ISyncable.SyncType.test_config);
        return new SimpleResponse(BaseResponse.SUCC_RESP);
    }

    @RequestMapping("list")
    @ResponseBody
    @IpLoginValid
    public ListResponse<TestConfig> add(){
        ListResponse<TestConfig> response = new ListResponse<TestConfig>();
        response.setResultList(testConfigService.selectAll());
        response.setResponse(BaseResponse.SUCC_RESP);

        return response;
    }
}
