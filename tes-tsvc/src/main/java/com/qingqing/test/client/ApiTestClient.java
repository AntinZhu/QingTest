package com.qingqing.test.client;

import com.qingqing.test.config.feign.exception.MyPbFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "apiTestClient", url = "http://localhost:8090", configuration = MyPbFeignConfiguration.class)
public interface ApiTestClient {

    @RequestMapping(path = "/apiTest/v1/mock/invoke", method = RequestMethod.POST, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String mock(@RequestParam("mockType") String mockType, @RequestParam("mockParam") String mockParam);
}


