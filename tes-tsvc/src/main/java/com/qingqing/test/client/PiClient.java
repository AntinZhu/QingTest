package com.qingqing.test.client;

import com.qingqing.common.auth.domain.UserType;
import com.qingqing.test.config.feign.MyPiFeignConfiguration;
import com.qingqing.test.feign.PtRequestInterceptor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@FeignClient(value = "piClient", url = "http://gateway.{env}.idc.cedu.cn", configuration = MyPiFeignConfiguration.class)
public interface PiClient {

    @RequestMapping(path = "/{url}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    String commonRequest(@PathVariable("url") String url, @RequestBody String request, @RequestHeader(name = PtRequestInterceptor.USER_ID) Long userId, @RequestHeader(PtRequestInterceptor.USER_TYPE) UserType userType);
}


