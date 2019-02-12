package com.qingqing.test.client;

import com.qingqing.test.config.feign.PayFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "payPbClient", url = "http://{host}/paysvc/api/pb/v1/payin/pay_notify/", configuration = PayFeignConfiguration.class)
public interface PayPbClient {

    @RequestMapping(path = "alipay", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    String alipayNotify(String param);

    @RequestMapping(path = "weixinpay", method = RequestMethod.POST, consumes = {MediaType.TEXT_XML_VALUE})
    @ResponseBody
    String weixinNotify(String param);

    @RequestMapping(path = "unionpay", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    String unionpayNotify(String param);

    @RequestMapping(path = "cmb_ywt_pay", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    String cmbNotify(String param);

    @RequestMapping(path = "baidu", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    String baiduNotify(String param);

    @RequestMapping(path = "ahm", method = RequestMethod.POST, consumes = {MediaType.TEXT_XML_VALUE})
    @ResponseBody
    String ahmNotify(String param);

    @RequestMapping(path = "cmblife", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseBody
    String cmblifeNotify(String param);

    @RequestMapping(path = "jd", method = RequestMethod.POST, consumes = {MediaType.TEXT_XML_VALUE})
    @ResponseBody
    String jdNotify(String param);
}
