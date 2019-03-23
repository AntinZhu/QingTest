package com.qingqing.test.aspect.qingswitch.determiner;

import org.springframework.core.Ordered;

/**
 * Created by zhujianxing on 2019/3/7.
 */
public interface ISwitchKeyDeterminer extends Ordered{

    boolean isOn(String switchKey, boolean defaultValue);

    boolean isSupport(String switchKey);

    String hintMessage();

    ISwitchKeyDeterminer ALWAYS_DEFAULT = new ISwitchKeyDeterminer() {
        @Override
        public int getOrder() {
            return Ordered.LOWEST_PRECEDENCE;
        }

        @Override
        public boolean isOn(String switchKey, boolean defaultValue) {
            return defaultValue;
        }

        @Override
        public boolean isSupport(String switchKey) {
            return true;
        }

        @Override
        public String hintMessage() {
            return "系统维护中，请稍后重试";
        }
    };
}
