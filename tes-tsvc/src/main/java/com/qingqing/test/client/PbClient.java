package com.qingqing.test.client;

import com.qingqing.test.config.feign.exception.MyPbFeignConfiguration;
import com.qingqing.test.feign.PtRequestInterceptor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "pbClient", url = "http://{host}", configuration = MyPbFeignConfiguration.class)
public interface PbClient {

    @RequestMapping(path = "{url}", method = RequestMethod.POST, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);

    @RequestMapping(path = "{url}", method = RequestMethod.GET, produces ={MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonGetRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(PtRequestInterceptor.HEADERS) String headers);
}


