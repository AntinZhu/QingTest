package com.qingqing.test.bean.common;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public class CommonLink<T> {

    private T t;
    private CommonLink next;

    public CommonLink(T t, CommonLink next) {
        this.t = t;
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public CommonLink getNext() {
        return next;
    }

    public void setNext(CommonLink next) {
        this.next = next;
    }
}
