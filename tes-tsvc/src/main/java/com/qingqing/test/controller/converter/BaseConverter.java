package com.qingqing.test.controller.converter;

import com.qingqing.api.proto.v1.ProtoBufResponse;
import com.qingqing.test.bean.base.KeyAndValue;
import com.qingqing.test.bean.base.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseConverter {

    public static BaseResponse convertBaseResponse(ProtoBufResponse.BaseResponse response){
        if(response == null){
            return null;
        }

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setError_code(response.getErrorCode());
        baseResponse.setError_message(response.getErrorMessage());
        baseResponse.setHint_message(response.getHintMessage());

        return baseResponse;
    }

    public static List<KeyAndValue> convertToKeyAndValue(Map<String, String> map){
        List<KeyAndValue> list = new ArrayList<>(map.size());
        for(Map.Entry<String, String> entry : map.entrySet()){
            list.add(new KeyAndValue(entry.getKey(), entry.getValue()));
        }
        
        return list;
    }
}
