package com.qingqing.test.aspect.masterslave.backup;

import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhujianxing on 2018/10/15.
 */
@Aspect
public class QingMasterSlaveDateSourceAspectBackup {
    private static final Logger logger = LoggerFactory.getLogger(QingMasterSlaveDateSourceAspectBackup.class);

    private static final ThreadLocal<ReadSlaveConfigClassInfo> slaveClassInfo = new ThreadLocal<>();
    private static final ThreadLocal<IsReadSlaveInfo> isReadSlaveInfo = new ThreadLocal<>();

    @Around(value = "@annotation(readSlaveDataSource)", argNames = "readSlaveDataSource")
    public Object aroundSlave(final ProceedingJoinPoint pjp, QingReadSlaveDataSource readSlaveDataSource) throws Throwable {
        ReadSlaveConfigClassInfo newClassesInfo = null;
        try{
            newClassesInfo = new ReadSlaveConfigClassInfo(Lists.newArrayList(readSlaveDataSource.enableClasses()), Lists.newArrayList(readSlaveDataSource.disableClasses()), readSlaveDataSource.defaultValue(), slaveClassInfo.get());
            slaveClassInfo.set(newClassesInfo);
            return pjp.proceed();
        }finally {
            if(newClassesInfo != null){
                slaveClassInfo.set(newClassesInfo.getPrevious());
            }
        }
    }

    @Around(value = "@annotation(qingSlaveReadable)", argNames = "qingSlaveReadable")
    public Object aroundSlave(final ProceedingJoinPoint pjp, QingSlaveReadable qingSlaveReadable) throws Throwable {
        IsReadSlaveInfo readSlaveInfo = null;
        try{
            readSlaveInfo = new IsReadSlaveInfo(canReadSlave(qingSlaveReadable.value()), isReadSlaveInfo.get());
            isReadSlaveInfo.set(readSlaveInfo);

            return pjp.proceed();
        }finally {
            if(readSlaveInfo != null){
                isReadSlaveInfo.set(readSlaveInfo.getPrevious());
            }
        }
    }

    private boolean canReadSlave(Class<?> methodClass){
        ReadSlaveConfigClassInfo configClassInfo = slaveClassInfo.get();
        if(configClassInfo != null){
            if(configClassInfo.getDisableClasses() != null && configClassInfo.getDisableClasses().contains(methodClass)){
                return false; // 明确标记不允许
            }

            if(configClassInfo.getEnableClasses() != null && configClassInfo.getEnableClasses().contains(methodClass)){
                return true;
            }

            return configClassInfo.getDefaultValue();
        }

        return false;
    }

    public static final boolean isReadSlave(){
        return isReadSlaveInfo.get() != null && isReadSlaveInfo.get().isReadSlave();
    }

    private class IsReadSlaveInfo{
        private final boolean isReadSlave;
        private final IsReadSlaveInfo previous;

        public IsReadSlaveInfo(boolean isReadSlave, IsReadSlaveInfo previous) {
            this.isReadSlave = isReadSlave;
            this.previous = previous;
        }

        public boolean isReadSlave() {
            return isReadSlave;
        }

        public IsReadSlaveInfo getPrevious() {
            return previous;
        }
    }

    private class ReadSlaveConfigClassInfo {
        private final List<Class<?>> enableClasses;
        private final List<Class<?>> disableClasses;
        private final boolean defaultValue;
        private final ReadSlaveConfigClassInfo previous;

        public ReadSlaveConfigClassInfo(List<Class<?>> enableClasses, List<Class<?>> disableClasses, boolean defaultValue, ReadSlaveConfigClassInfo previous) {
            this.enableClasses = enableClasses;
            this.disableClasses = disableClasses;
            this.defaultValue = defaultValue;
            this.previous = previous;
        }

        public List<Class<?>> getEnableClasses() {
            return enableClasses;
        }

        public List<Class<?>> getDisableClasses() {
            return disableClasses;
        }

        public boolean getDefaultValue() {
            return defaultValue;
        }

        public ReadSlaveConfigClassInfo getPrevious() {
            return previous;
        }
    }
}
