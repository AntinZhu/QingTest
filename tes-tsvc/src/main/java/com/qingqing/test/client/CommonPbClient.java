package com.qingqing.test.client;

import com.qingqing.test.config.feign.PayFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "commonPbClient", url = "https://", configuration = PayFeignConfiguration.class)
public interface CommonPbClient {

    @RequestMapping(path = "qyapi.weixin.qq.com/cgi-bin/webhook/send?key=42f74199-9edb-418c-b950-33f00fa1274a", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String wxNotify(@RequestBody String param);
}
