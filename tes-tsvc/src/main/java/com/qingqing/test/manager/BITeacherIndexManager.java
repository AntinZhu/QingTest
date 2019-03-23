package com.qingqing.test.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    public String queryTeacherIndex(Long teacherId, String indexName){
        String queryString = "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"teacher_id\":\"%s\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
        queryString = String.format(queryString, teacherId);

        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            String realIndexName = "bi_teacher_index*";
            if(indexName != null){
                realIndexName = indexName;
            }
            Response response = restClient.performRequest("GET", "/" + realIndexName + "/_search", params, entity);

            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);

            return jsonObject.getString("hits");
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    public String queryStudentIndex(Long studentId, String indexName){
        String queryString = "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"student_id\":\"%s\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
        queryString = String.format(queryString, studentId);

        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            String realIndexName = "bi_student_index*";
            if(indexName != null){
                realIndexName = indexName;
            }
            Response response = restClient.performRequest("GET", "/" + realIndexName + "/_search", params, entity);

            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);

            return jsonObject.getString("hits");
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    public boolean updateTeacherIndex(Long teacherId, String indexName, String updateString){
        HttpEntity updateEntity = new NStringEntity(updateString, ContentType.APPLICATION_JSON);
        try {
            Response response = restClient.performRequest("PUT", "/" + indexName + "/teacher/" + teacherId, params, updateEntity);
            int respCode = response.getStatusLine().getStatusCode();

            return respCode == 200 || respCode == 201;
        } catch (IOException e) {
            throw new QingQingRuntimeException("update teacher index error, teacherId:" + teacherId, e);
        }
    }

    public boolean updateStudentIndex(Long studentId, String indexName, String updateString){
        HttpEntity updateEntity = new NStringEntity(updateString, ContentType.APPLICATION_JSON);
        try {
            Response response = restClient.performRequest("PUT", "/" + indexName + "/student/" + studentId, params, updateEntity);
            int respCode = response.getStatusLine().getStatusCode();

            return respCode == 200 || respCode == 201;
        } catch (IOException e) {
            throw new QingQingRuntimeException("update student index error, studentId:" + studentId, e);
        }
    }

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

    public List<String> allIndex(){
        try{
            Response response = restClient.performRequest("GET", "/_cat/indices?v", new BasicHeader("1", "2"));
            logger.info(JsonUtil.format(response));
        } catch (IOException e) {
            throw new QingQingRuntimeException("query all index error:", e);
        }

        return null;
    }
}
