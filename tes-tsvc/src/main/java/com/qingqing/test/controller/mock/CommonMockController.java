package com.qingqing.test.controller.mock;

import com.qingqing.api.proto.v1.util.Common.SimpleLongRequest;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.StringUtils;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.common.IdAndBoolBean;
import com.qingqing.test.bean.common.response.ListResponse;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.domain.mock.MockType;
import com.qingqing.test.manager.CommonSyncManager;
import com.qingqing.test.manager.ISyncable;
import com.qingqing.test.manager.mock.rule.MockRuleManager;
import com.qingqing.test.service.mock.MockRuleService;
import com.qingqing.test.service.mock.MockTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Controller
@RequestMapping("/v1/mock")
public class CommonMockController {

    private static final Logger logger = LoggerFactory.getLogger(CommonMockController.class);

    @Autowired
    private MockRuleManager mockRuleManager;
    @Autowired
    private MockRuleService mockRuleService;
    @Autowired
    private MockTypeService mockTypeService;
    @Autowired
    private CommonSyncManager commonSyncManage;

    @RequestMapping(value = "invoke", method = RequestMethod.POST)
    @ResponseBody
    public String apply(
            @RequestParam(value = "mockType") String mockType,
            @RequestParam(value = "mockParam") String mockParam
    ) {
        MockRule mockRule = mockRuleManager.getMockRule(mockType, mockParam);
        if(mockRule == null){
            throw new RequestValidateException("no rule meet");
        }

        if(mockRule.getDelayMs() > 0){
            try {
                Thread.sleep(mockRule.getDelayMs());
            } catch (InterruptedException e) {
                logger.error("sleep error", e);
            }
        }

        return mockRule.getResp();
    }

    @RequestMapping(value = "type")
    public String typePage(){
        return "mock/type_list";
    }

    @RequestMapping(value = "type/list", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<MockType> getRuleTypeList(){
        ListResponse<MockType> result = new ListResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(mockTypeService.selectAll());
        return result;
    }

    @RequestMapping(value = "type/add", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse addRuleType(@RequestBody MockType mockType){
        mockTypeService.insert(mockType);

        return SimpleResponse.SUCC;
    }

    @RequestMapping(value = "type/delete", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse deleteRuleType(@RequestBody SimpleLongRequest request){
        mockTypeService.delete(request.getData());

        return SimpleResponse.SUCC;
    }

    @RequestMapping(value = "rule")
    public String rulePage(Model model, @RequestParam("mockType") String ruleType){
        model.addAttribute("mockType", ruleType);

        return "mock/rule_list";
    }

    @RequestMapping(value = "rule/edit")
    public String editRule(Model model, @RequestParam(value = "id", defaultValue = "0") Long id, @RequestParam(value = "mockType", defaultValue = "") String mockType){
        model.addAttribute("id", id);
        if(id > 0){
            model.addAttribute("bean", mockRuleService.findById(id));
        }
        if(!StringUtils.isEmpty(mockType)){
            model.addAttribute("mockType", mockType);
        }

        return "mock/edit_mock_rule";
    }

    @RequestMapping(value = "rule/list", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<MockRule> addgetRuleList(@RequestBody SimpleStringRequest request){
        List<MockRule> resultList = mockRuleService.selectByRuleType(request.getData());
        Collections.sort(resultList);

        ListResponse<MockRule> result = new ListResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(resultList);
        return result;
    }

    @RequestMapping(value = "rule/add", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse addRule(@RequestBody MockRule mockRule){
        if(mockRule.getId() != null){
            mockRuleService.update(mockRule);
        }else{
            mockRuleService.insert(mockRule);
        }

        commonSyncManage.sync(ISyncable.SyncType.mock_rule);
        return SimpleResponse.SUCC;
    }

    @RequestMapping(value = "rule/set_deleted", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse markDeleted(@RequestBody IdAndBoolBean request){
        mockRuleService.markDelete(request.getId(), request.getBool());

        return SimpleResponse.SUCC;
    }

    @RequestMapping(value = "rule/set_default", method = RequestMethod.POST)
    @ResponseBody
    public SimpleResponse markDefault(@RequestBody IdAndBoolBean request){
        if(request.getBool()){
            MockRule ruleType = mockRuleService.findById(request.getId());
            mockRuleService.resetDefault(ruleType.getMockType());
        }
        mockRuleService.markDefault(request.getId(), request.getBool());

        return SimpleResponse.SUCC;
    }
}
