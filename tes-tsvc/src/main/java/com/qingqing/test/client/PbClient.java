package com.qingqing.test.client;

import com.qingqing.test.config.feign.exception.MyPbFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "pbClient", url = "http://{host}", configuration = MyPbFeignConfiguration.class)
public interface PbClient {

    @RequestMapping(path = "{url}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonRequest(@PathVariable("url") String url, @RequestBody String request);
}


