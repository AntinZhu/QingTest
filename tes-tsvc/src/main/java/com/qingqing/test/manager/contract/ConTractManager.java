package com.qingqing.test.manager.contract;

import com.qingqing.common.util.JsonUtil;
import com.qingqing.test.bean.base.KeyAndValue;
import com.qingqing.test.bean.contract.ContractNotifyRequest;
import com.qingqing.test.client.PbClient;
import com.qingqing.test.util.ssq.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2020/6/13.
 */
@Component
public class ConTractManager {
    private static final Logger logger = LoggerFactory.getLogger(ConTractManager.class);

    private static final String NOTIFY_URL = "/contractsvc/api/pb/v1/contract/ssq/autoReceiveSignResult";

    @Value("${config.ssq.access_key}")
    private String accessKey;
    @Autowired
    private PbClient pbClient;

    public boolean notifyMock(ContractNotifyRequest request){
        String requestBody = String.format("{\"action\":\"signContract\",\"params\":{\"contractId\":\"%s\",\"account\":\"%s\",\"signerStatus\":2,\"errMsg\":\"success\",\"code\":\"0\",\"sid\":\"001\"}}", request.getSsqContractId(), request.getSsqAccountId());

        String ticket = RSAUtils.getRtick();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(RSAUtils.md5(requestBody.getBytes()))
                .append(ticket)
                .append(accessKey);
        String sign = RSAUtils.md5(stringBuilder.toString().getBytes());

        System.out.println("before Sign:" + stringBuilder.toString() + ", afterSign:" + sign);

        List<KeyAndValue> headers = new ArrayList<>();
        headers.add(new KeyAndValue("rtick", ticket));
        headers.add(new KeyAndValue("sign", sign));

        try{
            pbClient.commonRequest(NOTIFY_URL, requestBody, JsonUtil.getJsonFromObject(headers));
            return true;
        }catch (Exception e){
            logger.error("notify fail, request:" + JsonUtil.getJsonFromObject(request), e);
            return false;
        }
    }

}
