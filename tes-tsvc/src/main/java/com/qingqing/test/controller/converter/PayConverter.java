package com.qingqing.test.controller.converter;

import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV2Response;
import com.qingqing.api.proto.v1.Pay.OrderPayCmbInstallmentTypeItem;
import com.qingqing.api.proto.v1.Pay.OrderPayTypeInfo;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.KeyAndValue;
import com.qingqing.test.bean.pay.OrderPayTypeV3;
import com.qingqing.test.bean.pay.PayType;
import com.qingqing.test.bean.pay.PrePayBean;
import com.qingqing.test.bean.pay.PrePayBean.InstallmentConfigBean;
import com.qingqing.test.bean.pay.PrePayBean.InstallmentConfigItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujianxing on 2018/2/9.
 */
public class PayConverter {

    public static PrePayBean convertToPrePayBean(GeneralOrderPaymentSummaryV2Response response){
        PrePayBean prePayBean = new PrePayBean();
        prePayBean.setResponse(BaseConverter.convertBaseResponse(response.getResponse()));
        if(!BaseResponse.SUCC_CODE.equals(prePayBean.getResponse().getError_code())){
            return prePayBean;
        }

        List<KeyAndValue> supportPayTypeList = new ArrayList<>(response.getUncollapsedPayTypeCount() + response.getCollapsedPayTypeCount());
        List<InstallmentConfigBean> installmentConfigBeans = new ArrayList<>(response.getUncollapsedPayTypeCount() + response.getCollapsedPayTypeCount());

        String balanceAmount = "0";
        for(OrderPayTypeInfo payTypeInfo : response.getUncollapsedPayTypeList()){
            String payType = payTypeInfo.getPayType().name();
            String payTypeName = PayType.getName(payType);
            if(payTypeName != null){
                supportPayTypeList.add(new KeyAndValue(payType, payTypeName));
            }else{
                supportPayTypeList.add(new KeyAndValue(payType, "未识别类型（" + payType + "）"));
            }

            if(PayType.balance.getKey().equals(payType)){
                balanceAmount = String.valueOf(payTypeInfo.getAccountAmont());
            }

            if(payTypeInfo.getCmdInstallmentItemsCount() > 0){
                installmentConfigBeans.add(toInstallmentConfigBean(payType, payTypeInfo));
            }
        }

        for(OrderPayTypeInfo payTypeInfo : response.getCollapsedPayTypeList()){
            String payType = payTypeInfo.getPayType().name();
            String payTypeName = PayType.getName(payType);
            if(payTypeName != null){
                supportPayTypeList.add(new KeyAndValue(payType, payTypeName + "-(折叠)"));
            }else{
                supportPayTypeList.add(new KeyAndValue(payType, "未识别类型（" + payType + "）-（折叠）"));
            }

            if(PayType.balance.getKey().equals(payType)){
                balanceAmount = String.valueOf(payTypeInfo.getAccountAmont());
            }

            if(payTypeInfo.getCmdInstallmentItemsCount() > 0){
                installmentConfigBeans.add(toInstallmentConfigBean(payType, payTypeInfo));
            }
        }

        prePayBean.setSupportPayTypeList(supportPayTypeList);
        prePayBean.setNeedPayAmount(String.valueOf(response.getAllNeedExtraPay()));
        prePayBean.setBalanceAmount(balanceAmount);
        prePayBean.setInstallmentConfigs(installmentConfigBeans);

        return prePayBean;
    }

    private static InstallmentConfigBean toInstallmentConfigBean(String payType, OrderPayTypeInfo payTypeInfo){
        InstallmentConfigBean installmentConfigBean = new InstallmentConfigBean();
        installmentConfigBean.setPayType(payType);
        List<InstallmentConfigItemBean> itemList = new ArrayList<>(payTypeInfo.getCmdInstallmentItemsCount());
        for (OrderPayCmbInstallmentTypeItem orderPayCmbInstallmentTypeItem : payTypeInfo.getCmdInstallmentItemsList()) {
            InstallmentConfigItemBean item = new InstallmentConfigItemBean();
            item.setConfigId(orderPayCmbInstallmentTypeItem.getCmbInstallmentId());
            item.setStageNum(orderPayCmbInstallmentTypeItem.getStageNum());
            item.setServiceAmount(orderPayCmbInstallmentTypeItem.getServiceAmount());
            item.setStageAmount(orderPayCmbInstallmentTypeItem.getStageAmount());

            itemList.add(item);
        }
        installmentConfigBean.setItems(itemList);

        return installmentConfigBean;
    }

    public static PayType convertFromOrderPayTypeV3(OrderPayTypeV3 orderPayTypeV3){
        String payTypeName = orderPayTypeV3.getOrderPayTypeV2().name();

        return PayType.parseKey(payTypeName);
    }
}
