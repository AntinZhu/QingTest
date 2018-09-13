package com.qingqing.test.bean.common.response;

import com.qingqing.test.bean.base.InterfaceBaseResponse;

import java.util.List;

/**
 * Created by zhujianxing on 2018/9/12.
 */
public class ListResponse<T> extends InterfaceBaseResponse {
    private List<T> resultList;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}
