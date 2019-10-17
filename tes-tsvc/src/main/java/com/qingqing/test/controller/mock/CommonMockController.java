package com.qingqing.test.controller.mock;

import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.manager.mock.rule.MockRuleManager;
import com.qingqing.test.service.mock.MockOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2019/10/16.
 */
@Controller
@RequestMapping("/pi/v1/mock")
public class CommonMockController {

    private static final Logger logger = LoggerFactory.getLogger(CommonMockController.class);

    @Autowired
    private MockRuleManager mockRuleManager;
    @Autowired
    private MockOrderService mockOrderService;

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


}
