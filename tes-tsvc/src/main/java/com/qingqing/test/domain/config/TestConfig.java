package com.qingqing.test.domain.config;

import com.qingqing.common.intf.Composer;

import java.util.Date;

/**
 * Created by zhujianxing on 2019/3/5.
 */
public class TestConfig {

    public static final Composer<String, TestConfig> CONFIG_KEY_COMPOSER = new Composer<String, TestConfig>() {
        @Override
        public String getComposerId(TestConfig testConfig) {
            return testConfig.getConfigKey();
        }
    };

    private Long id;
    private String configKey;
    private String configValue;
    private String description;
    private Boolean isDeleted;
    private Date createTime;
    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
