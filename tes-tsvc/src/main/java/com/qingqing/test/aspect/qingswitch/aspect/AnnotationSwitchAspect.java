package com.qingqing.test.aspect.qingswitch.aspect;

import com.google.common.collect.Lists;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.common.util.CollectionsUtil;
import com.qingqing.common.util.StringUtils;
import com.qingqing.test.aspect.qingswitch.annotation.QingSwitch;
import com.qingqing.test.aspect.qingswitch.determiner.ISwitchKeyDeterminer;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhujianxing on 2019/3/7.
 */
@Aspect
public class AnnotationSwitchAspect{
    private static final String DEFAULT_HINT_MESSAGE = "系统维护中，请稍后重试";

    private final List<ISwitchKeyDeterminer> determinerList;

    public AnnotationSwitchAspect(List<ISwitchKeyDeterminer> determinerList) {
        if(CollectionsUtil.isNullOrEmpty(determinerList)){
            determinerList = Lists.newArrayList(ISwitchKeyDeterminer.ALWAYS_DEFAULT);
        }else{
            // 排序
            Collections.sort(determinerList, new Comparator<ISwitchKeyDeterminer>() {
                @Override
                public int compare(ISwitchKeyDeterminer o1, ISwitchKeyDeterminer o2) {
                    return o1.getOrder() - o2.getOrder();
                }
            });
        }

        this.determinerList = determinerList;
    }

    @Before(value = "@annotation(qingSwitch)", argNames = "qingSwitch")
    public void before(QingSwitch qingSwitch){
        ISwitchKeyDeterminer determiner = getDeterminer(qingSwitch.key());
        boolean isOn = qingSwitch.defaultValue();
        if(determiner != null){
            isOn = determiner.isOn(qingSwitch.key(), qingSwitch.defaultValue());
        }

        if(!isOn){
            String msg = "qing switch not open for key:" + qingSwitch.key();


            throw new ErrorCodeException(new SimpleErrorCode(qingSwitch.errorCode(), msg, getHintMessage(qingSwitch, determiner)), msg);
        }
    }

    private String getHintMessage(QingSwitch qingSwitch, ISwitchKeyDeterminer determiner){
        String hintMessage = DEFAULT_HINT_MESSAGE;

        if(!StringUtils.isEmpty(qingSwitch.hitMessage())){
            hintMessage = qingSwitch.hitMessage();
        }else if(determiner != null && !StringUtils.isEmpty(determiner.hintMessage())){
            hintMessage = determiner.hintMessage();
        }

        return hintMessage;
    }

    private ISwitchKeyDeterminer getDeterminer(String switchKey){
        if(!CollectionsUtil.isNullOrEmpty(determinerList)){
            for (ISwitchKeyDeterminer switchKeyDeterminer : determinerList) {
                if(switchKeyDeterminer.isSupport(switchKey)){
                    return switchKeyDeterminer;
                }
            }
        }

        return null;
    }
}
