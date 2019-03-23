package com.qingqing.test.aspect.masterslave;

import com.google.common.collect.Lists;
import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhujianxing on 2018/10/15.
 */
@Aspect
@Component
public class QingMasterSlaveDateSourceAspect{
    private static final Logger logger = LoggerFactory.getLogger(QingMasterSlaveDateSourceAspect.class);

    private static final ThreadLocal<HintManagerInfo> hintManagerInfo = new ThreadLocal<>();


    @Around(value = "@annotation(readSlaveDataSource)", argNames = "readSlaveDataSource")
    public Object aroundSlave(final ProceedingJoinPoint pjp, QingReadSlaveDataSource readSlaveDataSource) throws Throwable {
        try{
            newHintManagerInfo();

            return pjp.proceed();
        }finally {
            resetHintManagerInfo();
        }
    }

    private void newHintManagerInfo(){
        HintManagerInfo previous = hintManagerInfo.get();
        HintManagerHolder.clear();

        HintManager hitManager = HintManager.getInstance();
        hintManagerInfo.set(new HintManagerInfo(hitManager, previous));
    }

    private void resetHintManagerInfo(){
        HintManagerInfo nowHintManageInfo = hintManagerInfo.get();
        HintManagerInfo previousHintManagerInfo = nowHintManageInfo.getPrevious();

        hintManagerInfo.set(previousHintManagerInfo);
        HintManager previousHintManager = null;
        if(previousHintManagerInfo!= null){
            previousHintManager = previousHintManagerInfo.getHintManager();
        }
        HintManagerHolder.clear();
        HintManagerHolder.setHintManager(previousHintManager);
    }

    public class HintManagerInfo{
        private final HintManager hintManager;
        private final HintManagerInfo previous;

        public HintManagerInfo(HintManager hintManager, HintManagerInfo previous) {
            this.hintManager = hintManager;
            this.previous = previous;
        }

        public HintManager getHintManager() {
            return hintManager;
        }

        public HintManagerInfo getPrevious() {
            return previous;
        }
    }
}
