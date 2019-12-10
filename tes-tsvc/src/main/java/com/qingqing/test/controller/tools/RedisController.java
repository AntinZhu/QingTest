package com.qingqing.test.controller.tools;

import com.qingqing.api.proto.v1.util.Common;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.common.response.SingleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/redis")
public class RedisController {

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("get")
    @ResponseBody
    public SingleResponse<String> add(@RequestBody Common.SimpleStringRequest request){
        SingleResponse<String> result = new SingleResponse<>();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setResultList(stringRedisTemplate.opsForValue().get(request.getData()));
        return result;
    }
}
