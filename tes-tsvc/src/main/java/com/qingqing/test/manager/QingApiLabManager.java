package com.qingqing.test.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.qingqing.common.web.manager.HttpClientManagerV2;
import com.qingqing.test.util.QingFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujianxing on 2019/1/22.
 */

@Component
public class QingApiLabManager {
    private static final Logger logger = LoggerFactory.getLogger(QingApiLabManager.class);

    private static final String USER_CONVERT_URL = "https://lab.changingedu.com/apilab/page/convert/user";
    private static final String PHONE_DECODE_URL = "https://lab.changingedu.com/apilab/page/convert/aliPay";

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(3, 10, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    @Autowired
    private HttpClientManagerV2 httpClientManagerV2;

    public final Map<String, String> decodePhoneNumber(Collection<String> phoneLists, final String sessionId){
        Map<String, String> resultMap = new LinkedHashMap<>(phoneLists.size());
        for(final String phoneNumber : phoneLists){
            Future<String> result = THREAD_POOL.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    logger.info("start decode phoneNumber:" + phoneNumber);
                    String result =  decodePhoneNumber(phoneNumber, sessionId);
                    logger.info("end decode phoneNumber:" + phoneNumber);
                    return result;
                }
            });

            String decodePhoneNumber;
            try {
                decodePhoneNumber = result.get();
            }catch (Exception e) {
                logger.error("decode fail for value:{}" , phoneNumber, e);
                decodePhoneNumber = "ERROR";
            }
            resultMap.put(phoneNumber, decodePhoneNumber);
        }

        return resultMap;
    }

    private final String decodePhoneNumber(String encodePhoneNumber, String session){
//        String requestData = "{\"lan_ip\":\"172.22.7.82\",\"aliPayAccount\":\"\",\"encodeAliPayAccount\":\"%s\"}";
        JSONObject requestData = new JSONObject();
        requestData.put("lan_ip", "172.22.7.82");
        requestData.put("aliPayAccount", "");
        requestData.put("encodeAliPayAccount", encodePhoneNumber);

        String resp =  httpClientManagerV2.execPostJsonRequestWithParamsAndHeaders(PHONE_DECODE_URL, Collections.<String, String>emptyMap(), getCommonHeader(session), requestData);
        if(resp != null && resp != ""){
            JSONObject respObj = JSON.parseObject(resp);
            return respObj.getJSONObject("data").getString("aliPayAccount");
        }

        return "null";
    }

    public final String phoneToUserId(String userType, String phoneNumber, String session){
//        String requestData = "{\"lan_ip\":\"172.22.7.82\",\"userType\":\"%s\",\"userId\":\"\",\"qingqingUserId\":\"\",\"phoneNumber\":\"%s\"}";
        JSONObject requestData = new JSONObject();
        requestData.put("lan_ip", "172.22.7.82");
        requestData.put("userType", userType);
        requestData.put("userId", "");
        requestData.put("qingqingUserId", "");
        requestData.put("phoneNumber", phoneNumber);

        String resp =  httpClientManagerV2.execPostJsonRequestWithParamsAndHeaders(USER_CONVERT_URL, Collections.<String, String>emptyMap(), getCommonHeader(session), requestData);
        if(resp != null && resp != ""){
            JSONObject respObj = JSON.parseObject(resp);
            return respObj.getJSONObject("data").getString("userId");
        }

        return "null";
    }

    private final Map<String, String> getCommonHeader(String session){
        Map<String, String>
                headers = new HashMap<>();
        headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Cache-Control", "no-cache");
        headers.put("Connection", "keep-alive");
        headers.put("Content-Type", "application/json");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("Referer", "https://lab.changingedu.com/apilab/page/convert/panel");
        headers.put("Cookie", "SESSION=" + session);
        headers.put("Host", "lab.changingedu.com");
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36");

        return  headers;
    }

    public void setHttpClientManagerV2(HttpClientManagerV2 httpClientManagerV2) {
        this.httpClientManagerV2 = httpClientManagerV2;
    }

    public static void main(String[] args) throws IOException {
        HttpClientManagerV2 httpClientManagerV2 = new HttpClientManagerV2(3000, 30000,
                Sets.<String>newHashSet(), null, null);
        httpClientManagerV2.setMaxTotal(1023);
        httpClientManagerV2.setIsUseV1(false);

        QingApiLabManager labManager = new QingApiLabManager();
        labManager.setHttpClientManagerV2(httpClientManagerV2);

        List<String> phones = QingFileUtils.readLines("D://sql//pn.txt");
        for (String phone : phones) {
            String teacherId = labManager.phoneToUserId("teacher", phone, "b6c8ee5d-29e5-4a00-be29-735d3c7d26dc");
            System.out.println(phone + "," + teacherId);
        }
    }
}
