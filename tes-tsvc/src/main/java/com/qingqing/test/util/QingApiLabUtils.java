package com.qingqing.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by zhujianxing on 2019/1/21.
 */
public class QingApiLabUtils {

    private static final Logger logger = LoggerFactory.getLogger(QingApiLabUtils.class);

    private static final String USER_CONVERT_URL = "https://lab.changingedu.com/apilab/page/convert/user";
    private static final String PHONE_DECODE_URL = "https://lab.changingedu.com/apilab/page/convert/aliPay";

//    public static void main(String[] args) throws Exception {
//        String mode = args[0];
//        switch (mode){
//            case "phone":
//                String inputFile = args[1];
//                String session = args[2];
//                decodePhoneFile(inputFile, session);
//                break;
//            default:
//                System.err.println("unknown mode for value:" + mode);
//        }
//    }

    private static final void decodePhoneFile(String filePath, String session) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".csv"));

        String s = null;
        while((s = reader.readLine()) != null){
            writer.write(s + "," + decodePhoneNumber(s, session));
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public static final Map<String, String> decodePhoneNumber(Collection<String> phoneLists, final String sessionId, ExecutorService executor){
        Map<String, String> resultMap = new LinkedHashMap<>(phoneLists.size());
        for(final String phoneNumber : phoneLists){
            Future<String> result = executor.submit(new Callable<String>() {
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

    private static final String decodePhoneNumber(String encodePhoneNumber, String session){
        String requestData = "{\"lan_ip\":\"172.22.7.82\",\"aliPayAccount\":\"\",\"encodeAliPayAccount\":\"%s\"}";
        requestData = String.format(requestData, encodePhoneNumber);

        String resp =  doJsonPost(PHONE_DECODE_URL, requestData, getCommonHeader(session));
        if(resp != null && resp != ""){
            JSONObject respObj = JSON.parseObject(resp);
            return respObj.getJSONObject("data").getString("aliPayAccount");
        }

        return "null";
    }

    private static final String phoneToUserId(String userType, String phoneNumber, String session){
        String requestData = "{\"lan_ip\":\"172.22.7.82\",\"userType\":\"%s\",\"userId\":\"\",\"qingqingUserId\":\"\",\"phoneNumber\":\"%s\"}";
        requestData = String.format(requestData, userType, phoneNumber);

        try{
            String resp =  doJsonPost(USER_CONVERT_URL, requestData, getCommonHeader(session));
            if(resp != null && resp != ""){
                JSONObject respObj = JSON.parseObject(resp);
                return respObj.getJSONObject("data").getString("userId");
            }
        }catch(Exception e){
//            logger.error(e);
        }


        return "null";
    }

    private static final Map<String, String> getCommonHeader(String session){
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

    public static String doJsonPost(String httpUrl, String param, Map<String, String> headers) {

        CloseableHttpClient httpclient = null;
        CloseableHttpResponse h_response = null;
        try {
            httpclient =   HttpClientBuilder.create().build();

            HttpPost postMethod = new HttpPost(httpUrl);
//            postMethod.setConfig(requestConfig);
            postMethod.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36");
            for(Entry<String, String> entry : headers.entrySet()){
                postMethod.addHeader(entry.getKey(), entry.getValue());
            }

            postMethod.setEntity(new StringEntity(param, "UTF-8"));
            h_response = httpclient.execute(postMethod);
            HttpEntity repEntity = h_response.getEntity();

            int statusCode = h_response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                postMethod.abort();
            }
            return EntityUtils.toString(repEntity, "UTF-8");
        }catch (Exception e) {
            System.out.println("Exception " + System.currentTimeMillis());
            e.printStackTrace();
        } finally {
            if (h_response != null) {
                try {
                    h_response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        String[] phoneNumbers = new String[]{"18513350742","13962762517","15117109440","18838937670","15221171674","18172870258","19825043031","13080787809","15249691651","13282818330","15778049134","13806559332","15232162558","13488756096","13756406500","18652343165","15950539323","15905142098","13608518153","17805121977","18012900121","17356671830","18795851502","15077836330","13023810731","15261881411","18724612351","13478665927","18388169913","18328553133","15275256780","19908474076","13554074295","17610699394","17721368533","17721368533","17721368533","18251547250","15950587072","15374518972","13789821893","18392492056","13399857171","18652083138","15803510516","13701408775","17778070204","15057605732","18856926048","17390932330","15968867941","17625946178","13057513208","18789686829","17721368533","13193316849","15355112473","13386994682","18823676376","15751881806","15576633572","13267010314","13770725902","17778772319"};
        String sessionId = "acdc7bf3-909e-48de-bd64-06f941566439";
        for (String phoneNumber : phoneNumbers) {
            String result = phoneToUserId("ta", phoneNumber, sessionId);
            System.out.println(phoneNumber + "," + result);
        }
    }
}
