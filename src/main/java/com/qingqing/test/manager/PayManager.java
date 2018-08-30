package com.qingqing.test.manager;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.XMLFormatUtil;
import com.qingqing.common.util.converter.lang.BigDecimalUtil;
import com.qingqing.test.bean.pay.ahm.AhmNotifyRequest;
import com.qingqing.test.bean.pay.ahm.AhmNotifyResponse;
import com.qingqing.test.bean.pay.baidu.BaiduNotifyResponse;
import com.qingqing.test.bean.pay.cmblife.CmblifeNotifyResponse;
import com.qingqing.test.bean.pay.unionpay.UnionpayPrepayNotifyRequestBean;
import com.qingqing.test.bean.pay.weixin.WeixinPayNotifyRequestBean;
import com.qingqing.test.bean.pay.weixin.WeixinPayNotifyResponseBean;
import com.qingqing.test.client.PayPbClient;
import com.qingqing.test.controller.errorcode.PayErrorCode;
import com.qingqing.test.domain.pay.ThirdPayBrief;
import com.qingqing.test.service.pay.ThirdPayBriefService;
import com.qingqing.test.util.ObjectUtil;
import com.qingqing.test.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2018/8/24.
 */
@Component
public class PayManager {

    @Autowired
    private ThirdPayBriefService thirdPayBriefService;
    @Autowired
    private PayPbClient payPbClient;
    @Value("${weixinpay.app.appid}")
    private String weixinAppId;

    public boolean mockThirdPayNotify(String qingqingTradeNo){
        ThirdPayBrief thirdPayBrief = thirdPayBriefService.findByQingQingTradeNo(qingqingTradeNo);
        if(thirdPayBrief == null){
            throw new RequestValidateException("unknown qingqingTradeNo for ThirdPayBrief, qingqingTradeNo:" + qingqingTradeNo);
        }

        switch (thirdPayBrief.getThirdPaymentTypeV3().getOrderPayTypeV2()){
            case alipay:
            case hb_installment:
                return mockAlipayNotify(thirdPayBrief);
            case weixin_pay:
                return mockWeixinNotify(thirdPayBrief);
            case unionpay:
            case new_unionpay:
            case apple_pay:
                return mockUnionPayNotify(thirdPayBrief);
            case cmbywtpay:
                return mockCmbNotify(thirdPayBrief);
            case lovehaimi_pay:
                return mockAhmNotify(thirdPayBrief);
            case baidu_pay:
                return mockBaiduNotify(thirdPayBrief);
            case cmb_installment:
                return mockCmblifeNotify(thirdPayBrief);
            default:
                throw new ErrorCodeException(PayErrorCode.not_usupport_mock_notify_type, "not support mock pay notify, OrderPayTypeV2:" + thirdPayBrief.getThirdPaymentTypeV3().getOrderPayTypeV2());
        }
    }

    private boolean mockAlipayNotify(ThirdPayBrief thirdPayBrief){
        String param = "out_trade_no=" + thirdPayBrief.getQingqingTradeNo() +
                "&trade_no" + System.currentTimeMillis() +
                "&seller_email=张大爷" +
                "&trade_status=TRADE_SUCCESS" +
                "&total_fee=" + thirdPayBrief.getThirdPaymentAmount();
        String result = payPbClient.alipayNotify(param);

        return "success\r\n".equals(result);
    }

    private boolean mockWeixinNotify(ThirdPayBrief thirdPayBrief){
        WeixinPayNotifyRequestBean requestBean = new WeixinPayNotifyRequestBean();
        requestBean.setQingqingTradeNo(thirdPayBrief.getQingqingTradeNo());
        requestBean.setAppId(weixinAppId);

        String result = payPbClient.weixinNotify(XMLFormatUtil.getXmlStringByObject(requestBean));
        WeixinPayNotifyResponseBean responseBean = XMLFormatUtil.getObjectByXmlString(WeixinPayNotifyResponseBean.class, result);
        return "SUCCESS".equals(responseBean.getReturnCode());
    }

    private boolean mockUnionPayNotify(ThirdPayBrief thirdPayBrief){
        UnionpayPrepayNotifyRequestBean requestBean = new UnionpayPrepayNotifyRequestBean();
        requestBean.setOrderId(thirdPayBrief.getQingqingTradeNo());

        String request;
        try {
            request = UrlUtil.linkUrlRequestForObjectValue(ObjectUtil.objectToMap(requestBean));
        } catch (IllegalAccessException e) {
            throw new QingQingRuntimeException("link url error", e);
        }

        payPbClient.unionpayNotify(request);

        return true;
    }

    private boolean mockCmbNotify(ThirdPayBrief thirdPayBrief){
        String request = "Succeed=Y&CoNo=000013&Amount=10.00&Date=20160914&Msg=00210000132016091416291489600000000010&Signature=10|177|&BillNo=0000000018&MerchantPara=qingqing_order_desc=CMB_YWT_TEST_PAY20160914185108|qingqing_trade_no=" + thirdPayBrief.getQingqingTradeNo();

        payPbClient.cmbNotify(request);

        return true;
    }

    private boolean mockBaiduNotify(ThirdPayBrief thirdPayBrief){
        String param = "period=3&status=&sign=123&orderid=" + thirdPayBrief.getQingqingTradeNo();
        String result = payPbClient.baiduNotify(param);

        BaiduNotifyResponse notifyResult = JsonUtil.getObjectFromJson(result, BaiduNotifyResponse.class);

        return 0 == notifyResult.getStatus();
    }

    private boolean mockAhmNotify(ThirdPayBrief thirdPayBrief){
        AhmNotifyRequest notify = new AhmNotifyRequest();
        notify.setAppId("2");
        // TODO 此处金额校验有问题，在pay中修改
        notify.setAmt(Double.valueOf(BigDecimalUtil.mul(BigDecimalUtil.add(thirdPayBrief.getThirdPaymentAmount(), thirdPayBrief.getInstallmentPoundagePrice()), Double.valueOf(100))).intValue());
        notify.setAuditStatus(100);
        notify.setDesc("1212");
        notify.setProjectNo(thirdPayBrief.getQingqingTradeNo());
        notify.setTerm(12);
        notify.setSignvalue("as");

        String result = payPbClient.ahmNotify(JsonUtil.format(notify));
        AhmNotifyResponse notifyResponse = JsonUtil.getObjectFromJson(result, AhmNotifyResponse.class);

        return 100000 == notifyResponse.getCode();
    }

    private boolean mockCmblifeNotify(ThirdPayBrief thirdPayBrief){
        String param = "billno=" + thirdPayBrief.getQingqingTradeNo() + "&result=2&amount=100&paytype=1&refnum=" + System.currentTimeMillis() + "&ordernum=" +
                System.currentTimeMillis() + "&bankpayserial=" + System.currentTimeMillis() + "&cuprefno=123&shieldcardno=324";

        String result = payPbClient.cmblifeNotify(param);

        CmblifeNotifyResponse notifyResult = JsonUtil.getObjectFromJson(result, CmblifeNotifyResponse.class);

        return "1000".equals(String.valueOf(notifyResult.getRespCode()));
    }
}
