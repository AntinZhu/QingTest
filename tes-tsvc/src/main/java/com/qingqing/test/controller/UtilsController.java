package com.qingqing.test.controller;

import com.qingqing.api.proto.v1.UserProto;
import com.qingqing.api.proto.v1.util.Common.SimpleStringRequest;
import com.qingqing.common.auth.domain.User;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.web.protobuf.ProtoRequestBody;
import com.qingqing.test.bean.common.UserWithDataBean;
import com.qingqing.test.bean.common.response.SingleResponse;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/utils")
public class UtilsController {

    @Autowired
    private UserService userService;

    @RequestMapping("order/encode")
    @ResponseBody
    public SingleResponse<String> encode(@ProtoRequestBody SimpleStringRequest request){
        String orderIdStr = request.getData();

        String qingqingOrderId;
        try{
            qingqingOrderId = OrderIdEncoder.encodeOrderId(Long.valueOf(orderIdStr));
        }catch (Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "order id encode error","加密失败，请检查参数"),"convert order id error, value:" + orderIdStr, e);
        }

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(qingqingOrderId);
        return result;
    }

    @RequestMapping("order/decode")
    @ResponseBody
    public SingleResponse<Long> decode(@ProtoRequestBody SimpleStringRequest request){
        String qingqingOrderId = request.getData();

        Long orderId;
        try{
            orderId = OrderIdEncoder.decodeQingqingOrderId(qingqingOrderId);
        }catch (Exception e){
            throw new ErrorCodeException(new SimpleErrorCode(1001, "order id encode error","解密失败，请检查参数"),"convert order id error, value:" + qingqingOrderId, e);
        }

        SingleResponse<Long> result = new SingleResponse<Long>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        result.setResultList(orderId);
        return result;
     }

    @RequestMapping("user_phone/convert")
    @ResponseBody
    public SingleResponse<String> userPhoneConvert(@ProtoRequestBody UserProto.User request){
        String userPhoneNumber = userService.getUserPhone(UserType.valueOf(request.getUserType().getNumber()), request.getUserId());

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(userPhoneNumber != null){
            result.setResultList(userPhoneNumber);
        }
        return result;
    }

    @RequestMapping("phone_user/convert")
    @ResponseBody
    public SingleResponse<Long> userPhoneConvert(@RequestBody UserWithDataBean request){
        User user = userService.getUserByPhone(request.getUserType(), request.getData());

        SingleResponse<Long> result = new SingleResponse<Long>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(user != null){
            result.setResultList(user.getUserId());
        }
        return result;
    }

    @RequestMapping("user/encode")
    @ResponseBody
    public SingleResponse<String> encodeUserID(@ProtoRequestBody UserProto.User request){
        String qingqingUserId = userService.encodeUser(UserType.valueOf(request.getUserType().getNumber()), request.getUserId());

        SingleResponse<String> result = new SingleResponse<String>();
        result.setResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
        if(qingqingUserId != null){
            result.setResultList(qingqingUserId);
        }
        return result;
    }

}
