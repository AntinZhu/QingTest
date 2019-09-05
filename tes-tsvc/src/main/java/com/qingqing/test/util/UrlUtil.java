package com.qingqing.test.util;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.test.bean.test.BankValidateResult;
import com.tencentcloudapi.faceid.v20180301.models.BankCardVerificationResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by zhujianxing on 2018/1/24.
 */
public class UrlUtil {

    public static String linkUrlRequestForObjectValue(Map<String, Object> paramMap){
        return linkUrlRequestForObjectValue(paramMap, "=", "&");
    }

    public static String linkUrlRequestForObjectValue(Map<String, Object> paramMap, String link, String split){
        StringBuilder sb = new StringBuilder();

        Iterator iterator = paramMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, Object> entry = (Entry)iterator.next();
            if(entry.getValue() != null && (entry.getValue().toString()).trim().length() > 0) {
                sb.append(entry.getKey() + link + entry.getValue().toString() + split);
            }
        }

        String result = sb.toString();
        if(result.endsWith(split)) {
            result = result.substring(0, result.length() - split.length());
        }

        return result;
    }


    public static String linkUrlRequestForStringValueWithUrlEncode(Map<String, String> paramMap, String encode){
        StringBuilder sb = new StringBuilder();

        Iterator iterator = paramMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, String> entry = (Entry)iterator.next();
            if(entry.getValue() != null && ((String)entry.getValue()).trim().length() > 0) {
                try {
                    sb.append((String)entry.getKey() + "=" + URLEncoder.encode((String)entry.getValue(), encode) + "&");
                } catch (UnsupportedEncodingException e) {
                    throw new QingQingRuntimeException("url encode error", e);
                }
            }
        }

        String result = sb.toString();
        if(result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public static String linkUrlRequestForStringValue(Map<String, String> paramMap){
        StringBuilder sb = new StringBuilder();

        Iterator iterator = paramMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, String> entry = (Entry)iterator.next();
            if(entry.getValue() != null && ((String)entry.getValue()).trim().length() > 0) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }

        String result = sb.toString();
        if(result.endsWith("&")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("杨琳", "utf-8").replaceAll("\\+", "%20"));
//        String requestUrl = "a/b/n_q/";
//        int lastSplitIdx = requestUrl.lastIndexOf("/");
//        if(lastSplitIdx > -1 && lastSplitIdx < requestUrl.length() - 1){
//            System.out.println(requestUrl.substring(lastSplitIdx + 1));
//        }else{
//            System.out.println(requestUrl);
//        }
//        BankValidateResult result;
////
////        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
//        Credential cred = new Credential("AKIDTGbawzHe5lANZJn5La6a3Q1W9cLKo35Y", "EhpYfYwDkQ4tdDq5kzNdGTZ9PHYayvOd");
//
//        HttpProfile httpProfile = new HttpProfile();
//        httpProfile.setEndpoint("faceid.tencentcloudapi.com");
//
//        ClientProfile clientProfile = new ClientProfile();
//        clientProfile.setHttpProfile(httpProfile);
////
//        FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);
//
//        BankCardVerificationRequest request = new BankCardVerificationRequest();
//        request.setName("杨琳");
//        request.setBankCard("6215230500614350");
//        request.setIdCard("420683199402281267");
//
//        BankCardVerificationResponse response = null;
//        try {
//            response = client.BankCardVerification(request);
//            result = toBankValidateResult(response);
//        } catch (TencentCloudSDKException e) {
//            result = new BankValidateResult(false, false, "验证失败 当前系统无法验证，请稍后再试", false);
//        }finally {
//        }
//
//        System.out.println(JsonUtil.format(result));
//
//        String s= "{\"Result\": \"-3\",\"description\": \"?????\",\"requestId\": \"c6daaf7f-dbdc-4a9d-a20b-9a14ffdd8328\"}";
//        System.out.println(JsonUtil.format(JSON.parseObject(s, BankCardVerificationResponse.class)));
    }

    private static BankValidateResult toBankValidateResult(BankCardVerificationResponse response){
        String hintMsg;
        boolean isSuccess; // 是否允许添加
        boolean isCostTimes;
        boolean isValidateSucc;

        switch (response.getResult()){
            case "0":
                isSuccess = true;
                hintMsg= null;
                isValidateSucc = true;
                isCostTimes = true;
                break;
            case "-1":
            case "-2":
            case "-3":
            case "-4":
            case "-5":
                isSuccess = false;
                isValidateSucc = false;
                isCostTimes = true;

                hintMsg = "持卡人，卡号，身份证不一致，请检查后重新输入";
                break;
            case "-6":
            case "-7":
            case "-8":
            case "-9":
            case "-10":
            case "-11":
            case "-12":
            case "-13":
            case "-14":
            case "-15":
            case "-16":
                isSuccess = false;
                isValidateSucc = false;
                isCostTimes = true;

                hintMsg = "该卡状态异常，请更换其他银行卡";
                break;
            default:
                isSuccess = false;
                isValidateSucc = false;
                isCostTimes = false;
                hintMsg = "验证失败 当前系统无法验证，请稍后再试";
                break;
        }

        return new BankValidateResult(isSuccess, isCostTimes, hintMsg, isValidateSucc);
    }
}
