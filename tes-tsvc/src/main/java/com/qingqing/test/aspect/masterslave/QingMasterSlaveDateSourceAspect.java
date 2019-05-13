package com.qingqing.test.aspect.masterslave;

import com.google.common.collect.Lists;
import io.shardingjdbc.core.api.HintManager;
import io.shardingjdbc.core.hint.HintManagerHolder;
import io.shardingjdbc.core.jdbc.core.datasource.NamedDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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

    public static boolean isReadSlave(){
        HintManagerInfo hintManager = hintManagerInfo.get();

        return hintManager != null && hintManager.isSlave();
    }

    @Around(value = "@annotation(readSlaveDataSource)", argNames = "readSlaveDataSource")
    public Object aroundSlave(final ProceedingJoinPoint pjp, QingReadSlaveDataSource readSlaveDataSource) throws Throwable {
        try{
            newHintManagerInfo();

            return pjp.proceed();
        }finally {
            resetHintManagerInfo();
        }
    }

    @AfterReturning(value = "execution(* io.shardingjdbc.core.jdbc.core.datasource.MasterSlaveDataSource.*(..))", returning="dataSource")
    public void afterGetDataSource(NamedDataSource dataSource){
        logger.info("MasterSlaveDataSource.getDataSource result:" + dataSource.getName());
    }

    private void newHintManagerInfo(){
        HintManagerInfo previous = hintManagerInfo.get();

        hintManagerInfo.set(new HintManagerInfo(true, previous));
    }

    private void resetHintManagerInfo(){
        HintManagerInfo nowHintManageInfo = hintManagerInfo.get();
        HintManagerInfo previousHintManagerInfo = nowHintManageInfo.getPrevious();

        hintManagerInfo.set(previousHintManagerInfo);
    }

    public class HintManagerInfo{
        private final boolean isSlave;
        private final HintManagerInfo previous;

        public HintManagerInfo(boolean isSlave, HintManagerInfo previous) {
            this.isSlave = isSlave;
            this.previous = previous;
        }

        public boolean isSlave() {
            return isSlave;
        }

        public HintManagerInfo getPrevious() {
            return previous;
        }
    }
}
