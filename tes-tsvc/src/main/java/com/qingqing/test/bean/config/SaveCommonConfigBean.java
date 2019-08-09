package com.qingqing.test.bean.config;

import com.qingqing.test.domain.config.TestCommonConfig;

/**
 * Created by zhujianxing on 2018/9/28.
 */
public class SaveCommonConfigBean {
    private TestCommonConfig config;
    private Long parentCatelogId;

    public TestCommonConfig getConfig() {
        return config;
    }

    public void setConfig(TestCommonConfig config) {
        this.config = config;
    }

    public Long getParentCatelogId() {
        return parentCatelogId;
    }

    public void setParentCatelogId(Long parentCatelogId) {
        this.parentCatelogId = parentCatelogId;
    }
}
