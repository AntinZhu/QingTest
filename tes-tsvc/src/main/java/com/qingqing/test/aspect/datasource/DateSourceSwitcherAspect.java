package com.qingqing.test.aspect.datasource;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.hystrix.BackupDateSourceHystrixCommand;
import com.qingqing.test.hystrix.EmptyDateSourceHystrixCommand;
import com.qingqing.test.hystrix.NormalHystrixCommand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionException;

/**
 * Created by zhujianxing on 2018/10/15.
 */
@Aspect
//@Component
public class DateSourceSwitcherAspect implements PriorityOrdered{
    private static final Logger logger = LoggerFactory.getLogger(DateSourceSwitcherAspect.class);

    @Around(value = "@annotation(backupDataSource)", argNames = "backupDataSource")
    public Object aroundBackup(final ProceedingJoinPoint pjp, BackupDataSource backupDataSource) throws Throwable {
        return new BackupDateSourceHystrixCommand(backupDataSource.groupKey(), pjp, backupDataSource.dateSourceBeanName()).execute();
    }

    @Around(value = "@annotation(emptyDataSource)", argNames = "emptyDataSource")
    public Object aroundEmpty(final ProceedingJoinPoint pjp, EmptyDataSource emptyDataSource) throws Throwable {
        return new EmptyDateSourceHystrixCommand(emptyDataSource.groupKey(), pjp, emptyDataSource).execute();
    }

    @Around(value = "@annotation(errorCodeDataSource)", argNames = "errorCodeDataSource")
    public Object aroundErrorCode(final ProceedingJoinPoint pjp, ErrorCodeDataSource errorCodeDataSource) throws Throwable {
        NormalHystrixCommand codeHystrixCommand =  new NormalHystrixCommand(errorCodeDataSource.groupKey(), pjp);
        try{
            return codeHystrixCommand.execute();
        }catch (Throwable t){
            if(codeHystrixCommand.getFailedExecutionException() instanceof DataAccessException){
                throw new ErrorCodeException(new SimpleErrorCode(errorCodeDataSource.errorCode(), errorCodeDataSource.msg(), errorCodeDataSource.hintMessage()), errorCodeDataSource.msg(), codeHystrixCommand.getFailedExecutionException());
            }else{
                throw t;
            }
        }
    }

    @Around(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object aroundTransactional(final ProceedingJoinPoint pjp) throws Throwable {
        NormalHystrixCommand codeHystrixCommand =  new NormalHystrixCommand("default", pjp);
        try{
            return codeHystrixCommand.execute();
        }catch (Throwable t){
            if(codeHystrixCommand.getFailedExecutionException() instanceof TransactionException){
                throw new ErrorCodeException(new SimpleErrorCode(100001, "system error", "系统正在维护,请稍后再试"), "system error", codeHystrixCommand.getFailedExecutionException());
            }else{
                throw t;
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
