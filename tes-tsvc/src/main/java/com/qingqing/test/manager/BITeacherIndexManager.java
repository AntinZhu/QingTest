package com.qingqing.test.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.test.bean.base.KeyAndValue;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/1/30.
 */
@Component
public class BITeacherIndexManager {
    private static final Logger logger = LoggerFactory.getLogger(BITeacherIndexManager.class);

    @Autowired
    private RestClient restClient;
    private Map<String, String> params = Collections.emptyMap();;

    public String queryIndex(String queryString, String indexName){
        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Response response = restClient.performRequest("GET", "/" + indexName + "/_search", params, entity);

            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);

            return jsonObject.getString("hits");
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    public boolean updateIndex(String uniqueKey, String uniqueValue, String indexName, String updateString){
        HttpEntity updateEntity = new NStringEntity(updateString, ContentType.APPLICATION_JSON);
        try {
            Response response = restClient.performRequest("PUT", "/" + indexName + "/" + uniqueKey + "/" + uniqueValue, params, updateEntity);
            int respCode = response.getStatusLine().getStatusCode();

            return respCode == 200 || respCode == 201;
        } catch (IOException e) {
            throw new QingQingRuntimeException("update teacher index error, uniqueValue:" + uniqueValue, e);
        }
    }

    public boolean deleteIndex(String uniqueKey, String uniqueValue, String indexName){
        try {
            Response response = restClient.performRequest("DELETE", "/" + indexName + "/" + uniqueKey + "/" + uniqueValue, params);
            int respCode = response.getStatusLine().getStatusCode();

            return respCode == 200 || respCode == 201;
        } catch (IOException e) {
            throw new QingQingRuntimeException("update teacher index error, uniqueValue:" + uniqueValue, e);
        }
    }

    public List<KeyAndValue> allIndex(){
        List<KeyAndValue> resultList = new ArrayList<>(20);
        try{
            Response response = restClient.performRequest("GET", "/_cat/aliases?v&format=json&pretty", Collections.<String, String>emptyMap());
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONArray result = JSON.parseArray(responseBody);
            for(int idx = 0; idx < result.size(); idx++){
                JSONObject alia = result.getJSONObject(idx);
                resultList.add(new KeyAndValue(alia.getString("alias"), alia.getString("index")));
            }
        } catch (IOException e) {
            throw new QingQingRuntimeException("query all index error:", e);
        }

        return resultList;
    }
}
