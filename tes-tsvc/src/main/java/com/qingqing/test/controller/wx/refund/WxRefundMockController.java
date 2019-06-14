package com.qingqing.test.controller.wx.refund;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.XMLUtil;
import com.qingqing.test.bean.mock.MockOrder;
import com.qingqing.test.domain.mock.InterfaceType;
import com.qingqing.test.domain.mock.MockRule;
import com.qingqing.test.manager.mock.rule.MockRuleManager;
import com.qingqing.test.service.mock.MockOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhangyang on 2018/2/3.
 */
@Controller
@RequestMapping("/pi/v1/wx/refund")
public class WxRefundMockController {

    private static final Logger logger = LoggerFactory.getLogger(WxRefundMockController.class);

    @Autowired
    private MockRuleManager mockRuleManager;
    @Autowired
    private MockOrderService mockOrderService;

    @RequestMapping(value = "mock", method = RequestMethod.POST)
    @ResponseBody
    public String apply(
        @RequestBody String requestXml,
        @RequestParam(value = "method") String method
    ) {
        InterfaceType interfaceType = convertToInterfaceType(method);
        switch (interfaceType){
            case wx_refund_apply:
                MockOrder mockOrder = convertToOrder(requestXml);
                return mock(interfaceType, mockOrder);
            case wx_refund_query:
                Long id = getRefundId(requestXml);
                MockOrder mockOrderInDB = mockOrderService.findById(id);
                if(mockOrderInDB == null){
                    throw new RequestValidateException("unknown order for orderId:" + id);
                }
                return mock(interfaceType, mockOrderInDB);
            default:
                throw new QingQingRuntimeException("unknown interface type for mock, interfaceType:" + interfaceType);
        }
    }

    private String mock(InterfaceType interfaceType, MockOrder mockOrder){
        MockRule mockRule = mockRuleManager.getMockRule(interfaceType, mockOrder);
        if(mockRule == null){
            throw new RequestValidateException("no rule meet");
        }

        if(mockRule.getNeedRecordOrder() && mockOrder.getId() == null){
            mockOrderService.insert(mockOrder);
        }

        return convertResp(mockRule.getResp(), mockOrder);
    }

    private static String convertResp(String resp, MockOrder mockOrder){
        String result =  resp;
        logger.info("before result:" + result);
        logger.info("before result:" + result + "中文");

        result = result.replaceAll("%refund_id%", String.valueOf(mockOrder.getId()));
        result = result.replaceAll("%refund_fee%", String.valueOf(Double.valueOf(mockOrder.getAmount()).intValue()));

        logger.info("result:" + result);
        return result;
    }

    private Long getRefundId(String requestXml){
        Map<String, String> resultMap = XMLUtil.getElementsInTopLevel(requestXml);

        return Long.valueOf(resultMap.get("refund_id"));
    }

    private InterfaceType convertToInterfaceType(String method){
        switch (method){
            case "apply":
                return InterfaceType.wx_refund_apply;
            case "query":
                return InterfaceType.wx_refund_query;
            default:
                throw new RequestValidateException("unknown method for mock, method:" + method);
        }
    }

    private static MockOrder convertToOrder(String xml){
        Map<String, String> resultMap = XMLUtil.getElementsInTopLevel(xml);

        MockOrder mockOrder = new MockOrder();
        mockOrder.setQingqingOrderId(resultMap.get("out_refund_no"));
        mockOrder.setAmount(Double.valueOf(resultMap.get("refund_fee")));

        return mockOrder;
    }
}
