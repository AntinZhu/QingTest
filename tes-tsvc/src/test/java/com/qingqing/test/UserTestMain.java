package com.qingqing.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingqing.common.exception.QingQingRuntimeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by zhujianxing on 2018/9/30.
 */
public class UserTestMain {

    public final RestClient restClient = RestClient
            .builder(new HttpHost("172.20.13.216", 9200, "http"))
            .build();

    @Test
    public void search() throws IOException {
        Map<String, String> params = Collections.emptyMap();

        String queryString = "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"teacher_id\":\"39160\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";

        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);

        try {
            Response response = restClient.performRequest("GET", "/bi_teacher_index*/_search", params, entity);
            System.out.println(response.getStatusLine().getStatusCode());
            String responseBody = null;

            responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("******************************************** ");

            JSONObject jsonObject = JSON.parseObject(responseBody);


            System.out.println(jsonObject.get("hits"));

            String updateString = "{\"bi_etl_date\":\"2019-01-16\",\"tr_high_voltage_line\":0,\"teacher_id\":39160,\"tr_early_warning_line\":1,\"bi_etl_data_time\":\"2019-01-16 18:35:45\"}";
            HttpEntity updateEntity = new NStringEntity(updateString, ContentType.APPLICATION_JSON);
            response = restClient.performRequest("PUT", "/bi_teacher_index_20190116_18_22_11/teacher/39160", params, updateEntity);

            System.out.println(response.getStatusLine().getStatusCode());
        }catch (ResponseException e){
            e.printStackTrace();
        }
        System.out.println("23333");
    }

    @Test
    public void testa(){
        Map<String, String> params = Collections.emptyMap();
        String queryString = "{\"query\":{\"bool\":{\"must\":[{\"term\":{\"student_id\":\"%s\"}},{\"term\":{\"teacher_id\":\"%s\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}: ";
        queryString = String.format(queryString, 128985, 234);

        HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
        try {
            Response response = restClient.performRequest("GET", "/bi_tr_stu_index*/_search", params, entity);

            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(responseBody);

            System.out.println(jsonObject.getString("hits"));
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    @Test
    public void testAlias(){
        try {
            Response response = restClient.performRequest("GET", "/_cat/aliases?v&format=json&pretty", Collections.<String, String>emptyMap());

            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(responseBody);
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    @Test
    public void testAlias_2(){
        try {
            Response response = restClient.performRequest("GET", "/_cat/aliases/bi_tr_stu_index?v&format=json&pretty", Collections.<String, String>emptyMap());

            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(responseBody);
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    @Test
    public void testAllocation(){
        try {
            Response response = restClient.performRequest("GET", "/_cat/allocation?v&format=json&pretty", Collections.<String, String>emptyMap());

            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(responseBody);
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    @Test
    public void testCount_1(){
        try {
            Response response = restClient.performRequest("GET", "/_cat/count?v&format=json&pretty", Collections.<String, String>emptyMap());

            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(responseBody);
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }

    @Test
    public void testCount_2(){
        try {
            Response response = restClient.performRequest("GET", "/_cat/count/bi_tr_stu_index?v&format=json&pretty", Collections.<String, String>emptyMap());

            String responseBody = EntityUtils.toString(response.getEntity());

            System.out.println(responseBody);
        } catch (IOException e) {
            throw new QingQingRuntimeException("", e);
        }
    }
}
