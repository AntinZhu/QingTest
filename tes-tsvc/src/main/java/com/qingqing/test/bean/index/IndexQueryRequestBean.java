package com.qingqing.test.bean.index;

/**
 * Created by zhujianxing on 2019/1/31.
 */
public class IndexQueryRequestBean {
    private String queryString;
    private String indexName;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}
