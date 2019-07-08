package com.qingqing.test.manager;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public interface ISyncable {
    public static enum SyncType{
        all, proto_name, user_ip;
    }

    void sync();

    SyncType[] syncTypes();
}
