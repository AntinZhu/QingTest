package com.qingqing.test.manager;

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
}
