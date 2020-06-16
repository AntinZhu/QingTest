package com.qingqing.test.controller.contract;

import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.StringUtils;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.common.web.protobuf.ProtoRespGenerator;
import com.qingqing.common.web.protobuf.ProtoResponseBody;
import com.qingqing.test.aspect.validate.IpLoginValid;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.IdAndBoolBean;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.bean.contract.ContractNotifyRequest;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.domain.mock.MockType;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable;
import com.qingqing.test.manager.contract.ConTractManager;
import com.qingqing.test.manager.mock.rule.MockRuleManager;
import com.qingqing.test.service.mock.MockRuleService;
import com.qingqing.test.service.mock.MockTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Controller
@RequestMapping("/v1/contract")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ConTractManager conTractManager;

    @RequestMapping(value = "ahaya")
    public String typePage( @RequestParam(value = "env", defaultValue = "dev") String env,
                            @RequestParam(value = "uid", defaultValue = "0") Long userId,
                            @RequestParam(value = "uty", defaultValue = "") String userType,
                            @RequestParam(value = "def", defaultValue = "{}") String defaultObj,
                            @RequestParam(value = "paramId", defaultValue = "0") Long paramId,
                            @RequestParam(value = "inv", defaultValue = "0") String inv,
                            @RequestParam(value = "gnp", defaultValue = "0") int goToNextPage,
                            Model model
    ){
        model.addAttribute("paramExampleId", paramId);
        model.addAttribute("env", env);
        model.addAttribute("userId", userId);
        model.addAttribute("userType", userType);
        model.addAttribute("defaultObj", defaultObj);
        model.addAttribute("inv", inv);
        model.addAttribute("goToNextPage", goToNextPage);

        return "contract/mock_contract";
    }


    @RequestMapping("mock_notify")
    @ProtoResponseBody
    public ProtoBufResponse.SimpleResponse mockThirdPay(@RequestBody ContractNotifyRequest request){
        boolean notifyResult = conTractManager.notifyMock(request);

        if(notifyResult){
            return ProtoRespGenerator.SIMPLE_SUCC_RESP;
        }else{
            return ProtoBufResponse.SimpleResponse.newBuilder()
                    .setResponse(ProtoBufResponse.BaseResponse.newBuilder().setErrorCode(1).setHintMessage("模拟合同签署成功通知失败"))
                    .build();
        }
    }
}
