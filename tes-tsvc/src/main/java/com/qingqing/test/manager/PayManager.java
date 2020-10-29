package com.qingqing.test.manager;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.exception.RequestValidateException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.XMLFormatUtil;
import com.qingqing.common.util.converter.lang.BigDecimalUtil;
import com.qingqing.test.bean.pay.ThirdPayBriefExtend;
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
import feign.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujianxing on 2018/8/24.
 */
@Component
public class PayManager {

    private static final Logger logger = LoggerFactory.getLogger(PayManager.class);

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
            case jd_pay:
                return mockJdNotify(thirdPayBrief);
            case alipay_chinaums:
            case weixin_pay_chinaums:
                return mockChinaums(thirdPayBrief);
            case cmbc_installment:
                return mockCmbc(thirdPayBrief);
            default:
                throw new ErrorCodeException(PayErrorCode.not_usupport_mock_notify_type, "not support mock pay notify, OrderPayTypeV2:" + thirdPayBrief.getThirdPaymentTypeV3().getOrderPayTypeV2());
        }
    }

    private boolean mockCmbc(ThirdPayBrief thirdPayBrief){
        String notifyParam = "{" +
                "  \"s\" : \"SUCCESS\"," +
                "  \"msg\" : \"交易成功\"," +
                "  \"cipRs\" : \"{\\\"usedPoint\\\":0,\\\"realyOrderAmt\\\":9.9,\\\"orderNum\\\":\\\"" + thirdPayBrief.getQingqingTradeNo() + "\\\",\\\"finishedTime\\\":\\\"2020-01-07 14:49:19\\\",\\\"flowSeq\\\":\\\"51135202001071214438848650543105\\\",\\\"orderAmt\\\":" + thirdPayBrief.getThirdPaymentAmount() +"}\"," +
                "  \"cipRsBean\" : {" +
                "    \"flowSeq\" : \"51135202001071214438848650543105\"," +
                "    \"orderNum\" : \"1578313250014T178293109510\"," +
                "    \"orderAmt\" : 10000.0," +
                "    \"realyOrderAmt\" : 9.9," +
                "    \"finishedTime\" : \"2020-01-07 14:49:19\"," +
                "    \"usedPoint\" : 0" +
                "  }" +
                "}";


        Map<String, String> params = new HashMap<>();
        params.put("_cryptDatas", notifyParam);
        params.put("h5ReturnUrl", "aHR0cHM6Ly93d3cuYmFpZHUuY29t");

        String result = payPbClient.cmbcNotify(params);

        logger.info("mockCmbc result:" + result);
        return true;
    }

    private boolean mockChinaums(ThirdPayBrief thirdPayBrief){
        Integer channelId = thirdPayBrief.getThirdPaymentTypeV3().getValue();

        String chinaumsTradeNo = "";
        if(!CollectionsUtil.isNullOrEmpty(thirdPayBrief.getExtendList())){
            for (ThirdPayBriefExtend thirdPayBriefExtend : thirdPayBrief.getExtendList()) {
                if(ThirdPayBriefExtend.CHINAUMS_TRADE_NO.equals(thirdPayBriefExtend.getRefType())){
                    chinaumsTradeNo = thirdPayBriefExtend.getExtendValue();
                    break;
                }
            }
        }

        String param = "buyerUsername=152****2089&" +
                "eF=cWZN&" +
                "payTime=2020-05-12 19:25:52&" +
                "connectSys=ALIPAY&" +
                "sign=test&" +
                "merName=全渠道&" +
                "mid=898310148160568&" +
                "invoiceAmount=3&" +
                "settleDate=2020-05-12&" +
                "acqSpId=KFTEST12&" +
                "billFunds=花呗:3&" +
                "buyerId=2088012948980027&" +
                "tid=88880001&" +
                "instMid=H5DEFAULT&" +
                "receiptAmount=3&" +
                "couponAmount=0&" +
                "targetOrderId=2020051222001480021450699553&" +
                "cardAttr=BALANCE&" +
                "billFundsDesc=花呗支付0.03元。&" +
                "orderDesc=测试支付&" +
                "seqId=00737800104N&" +
                "merOrderId=" + chinaumsTradeNo + "&" +
                "targetSys=Alipay 2.0&" +
                "totalAmount=3&" +
                "createTime=2020-05-12 19:25:29&" +
                "buyerPayAmount=3&" +
                "notifyId=5cbaef05-7bbf-4849-832e-3edcfff49cc6&" +
                "subInst=101300&" +
                "status=TRADE_SUCCESS";

        String result = payPbClient.chinaumsNotify(param, channelId);

        logger.info("chinaumsNotify result:" + result);
        return result != null && result.startsWith("SUCCESS");
    }

    private boolean mockAlipayNotify(ThirdPayBrief thirdPayBrief){
        String param = "out_trade_no=" + thirdPayBrief.getQingqingTradeNo() +
                "&trade_no=" + System.currentTimeMillis() +
                "&seller_email=张大爷" +
                "&trade_status=TRADE_SUCCESS" +
                "&total_fee=" + thirdPayBrief.getThirdPaymentAmount();
        String result = payPbClient.alipayNotify(param);

        return result != null && result.startsWith("success");
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
        String param = "period=3&status=8&sign=123&orderid=" + thirdPayBrief.getQingqingTradeNo();
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

    private boolean mockJdNotify(ThirdPayBrief thirdPayBrief){
        String param = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                "<jdpay>" +
                "  <version>V2.0</version>" +
                "  <merchant>22294531</merchant>" +
                "<result>" +
                "  <code>000000</code>" +
                "  <desc>success</desc>" +
                "</result>" +
                "<device>6220</device>" +
                "<tradeNum>" + thirdPayBrief.getQingqingTradeNo() + "</tradeNum>" +
                "<tradeType>0</tradeType>" +
                "<sign>HM8NC+Epqb1txYYJ3keySPwFXJVydf3H8oVSk85B375QhSsy6sOmIoIKI686fXmRR+30r2+OC7LWryFfRqAGpa1OO7OlDrqzNZoJoDYfAYJysCI5S1u9rNKvXagQg1S7TkqkWBTbHMQJsvfVwaMujYAbCaKrL25WTQVcPIWby70=</sign>" +
                "<note>备注信息</note>" +
                "<amount>3140</amount>" +
                "<status>2</status>" +
                "<payList>" +
                "  <pay>" +
                "    <payType>3</payType>" +
                "    <amount>1500</amount>" +
                "    <currency>CNY</currency>" +
                "    <tradeTime>20180101093516</tradeTime>" +
                " </pay>" +
                " <pay>" +
                "   <payType>0</payType>" +
                "   <amount>500</amount>" +
                "   <currency>CNY</currency>" +
                "   <tradeTime>20180101093516</tradeTime>" +
                "   <detail>" +
                "     <cardHolderName>*三</cardHolderName>" +
                "     <cardHolderMobile>150****1234</cardHolderMobile>" +
                "     <cardHolderType>0</cardHolderType>" +
                "     <cardHolderId>****3265</cardHolderId>" +
                "     <cardNo>622088****4001</cardNo>     " +
                "     <bankCode>ICBC</bankCode>" +
                "     <cardType>1</cardType>" +
                "   </detail>" +
                "  </pay>" +
                "</payList>" +
                "</jdpay>";

        String result = payPbClient.jdNotify(param);

        return "success\r".equals(String.valueOf(result));
    }
}
