package com.qingqing.test.manager;

import com.alibaba.fastjson.JSONObject;
import com.qingqing.test.bean.base.KeyAndValue;
import com.qingqing.test.client.CommonPbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhujianxing on 2019/7/9.
 */
@Component
public class WxNotifyManager {

    @Autowired
    private CommonPbClient commonPbClient;

    public void selfNotify(String content){
        commonPbClient.selfNotify(content);
    }

    public void markdown(String title, KeyAndValue ... items){
        StringBuilder content = new StringBuilder(title);
        for (KeyAndValue item : items) {
            content.append("\n                >" + item.getKey() + ": <font color=\"comment\">" + item.getValue() + "</font>");
        }


        JSONObject mark = new JSONObject();
        mark.put("content", content.toString());

        JSONObject obj = new JSONObject();
        obj.put("msgtype", "markdown");
        obj.put("markdown", mark);

        selfNotify(obj.toJSONString());
    }
}
