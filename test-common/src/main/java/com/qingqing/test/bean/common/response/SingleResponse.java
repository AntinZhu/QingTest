package com.qingqing.test.bean.common.response;

import com.qingqing.test.bean.base.InterfaceBaseResponse;

/**
 * Created by zhujianxing on 2018/9/12.
 */
public class SingleResponse<T> extends InterfaceBaseResponse {
    private T resultList;

    public T getResultList() {
        return resultList;
    }

    public void setResultList(T resultList) {
        this.resultList = resultList;
    }
}
