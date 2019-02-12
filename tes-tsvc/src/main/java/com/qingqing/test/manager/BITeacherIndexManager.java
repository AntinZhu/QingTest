package com.qingqing.test.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.exception.QingQingRuntimeException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by zhujianxing on 2019/1/30.
 */
@Component
public class BITeacherIndexManager {

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

    public String queryStudentTeacherIndex(Long teacherId, Long studentId, String indexName){
        String queryString = "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"student_id\":\"%s\"}},{\"term\":{\"teacher_id\":\"%s\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}: ";
        queryString = String.format(queryString, studentId, teacherId);

        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            String realIndexName = "bi_tr_stu_index*";
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

    public boolean updateStudentTeacherIndex(Long teacherId, Long studentId, String indexName, String updateString){
        HttpEntity updateEntity = new NStringEntity(updateString, ContentType.APPLICATION_JSON);
        try {
            Response response = restClient.performRequest("PUT", "/" + indexName + "/student-teacher/" + studentId + "-" + teacherId, params, updateEntity);
            int respCode = response.getStatusLine().getStatusCode();

            return respCode == 200 || respCode == 201;
        } catch (IOException e) {
            throw new QingQingRuntimeException("update teacher index error, teacherId:" + teacherId, e);
        }
    }
}
