package com.qingqing.test.aspect.externalsystem;

import com.qingqing.common.exception.ErrorCodeException;
import com.qingqing.test.controller.errorcode.SimpleErrorCode;
import com.qingqing.test.hystrix.dataSource.NormalHystrixCommand;
import com.qingqing.test.hystrix.inter.ExternalSystemHystrixCommand;
import com.qingqing.test.hystrix.inter.QingFusingStrategy;
import com.qingqing.test.hystrix.inter.QingFusingStrategyHolder;
import com.qingqing.test.hystrix.inter.QingFusingStrategyHolder.QingFusingStrategyBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

/**
 * Created by zhujianxing on 2018/10/15.
 */
@Aspect
public class QingExternalSystemFusingAspect {
    private static final Logger logger = LoggerFactory.getLogger(QingExternalSystemFusingAspect.class);

    @Around(value = "@annotation(qingExternalSystemFusing)", argNames = "qingExternalSystemFusing")
    public Object aroundErrorCode(final ProceedingJoinPoint pjp, QingExternalSystemFusing qingExternalSystemFusing) throws Throwable {
        logger.info("can you see me");
        ExternalSystemHystrixCommand hystrixCommand=  new ExternalSystemHystrixCommand(qingExternalSystemFusing.groupKey(), pjp);
        try{
            return hystrixCommand.execute();
        }catch (Throwable t){
            if(hystrixCommand.getFailedExecutionException() instanceof ErrorCodeException){
                throw (ErrorCodeException)hystrixCommand.getFailedExecutionException();
            }

            QingFusingStrategyBean<Object> fusingStrategyBean =  QingFusingStrategyHolder.getQingFusingStrategyBean();
            if(fusingStrategyBean != null){
                QingFusingStrategy fusingStrategy = fusingStrategyBean.getFusingMode();
                switch (fusingStrategy){
                    case error_code:
                        throw new ErrorCodeException(fusingStrategyBean.getErrorCodeInterface(), fusingStrategyBean.getErrorCodeInterface().getMsg());
                    case demotion:
                        return fusingStrategyBean.getFusingValue().get();
                    default:
                        throw new RuntimeException("unknown fusingStrategy for value:" + fusingStrategy);
                }
            }

            throw new ErrorCodeException(new com.qingqing.common.exception.SimpleErrorCode(qingExternalSystemFusing.errorCode(), qingExternalSystemFusing.msg(), qingExternalSystemFusing.hintMessage()), qingExternalSystemFusing.msg());
        }finally {
            QingFusingStrategyHolder.clear();
        }
    }
}
