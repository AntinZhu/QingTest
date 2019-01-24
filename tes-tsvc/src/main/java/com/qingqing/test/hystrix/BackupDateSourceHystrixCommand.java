package com.qingqing.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.qingqing.common.exception.QingQingRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhujianxing on 2018/10/16.
 */
public class BackupDateSourceHystrixCommand extends HystrixCommand<Object> {
    private static final Logger logger = LoggerFactory.getLogger(BackupDateSourceHystrixCommand.class);

    private static ThreadLocal<String> BEAN_NAME = new ThreadLocal<>();
    private final ProceedingJoinPoint pjp;
    private final String dataSourceBeanName;

    public BackupDateSourceHystrixCommand(String groupKey, ProceedingJoinPoint pjp, String dataSourceBeanName) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)).andCommandKey(HystrixCommandKey.Factory.asKey(groupKey)));
        this.pjp = pjp;
        this.dataSourceBeanName = dataSourceBeanName;
    }

    @Override
    protected Object run() throws Exception {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new Exception(throwable);
        }
    }

    @Override
    protected Object getFallback() {
        if(this.circuitBreaker.isOpen()){
            try {
                BEAN_NAME.set(dataSourceBeanName);
                return pjp.proceed();
            } catch (Throwable throwable) {
                logger.error("", throwable);
            }finally{
                BEAN_NAME.remove();
            }
        }

        Throwable throwable = getExecutionException();
        if(throwable != null){
            throw new QingQingRuntimeException("invoke error", throwable);
        }

        return null;
    }

    public static final String getBindDataSourceBeanName(){
        return BEAN_NAME.get();
    }
}
