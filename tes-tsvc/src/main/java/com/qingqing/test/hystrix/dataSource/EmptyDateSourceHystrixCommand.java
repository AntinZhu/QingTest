package com.qingqing.test.hystrix.dataSource;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.qingqing.test.aspect.datasource.EmptyDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Created by zhujianxing on 2018/10/16.
 */
public class EmptyDateSourceHystrixCommand extends HystrixCommand<Object> {
    private static final Logger logger = LoggerFactory.getLogger(EmptyDateSourceHystrixCommand.class);

    private final ProceedingJoinPoint pjp;
    private final EmptyDataSource emptyDataSource;

    public EmptyDateSourceHystrixCommand(String groupKey, ProceedingJoinPoint pjp, EmptyDataSource emptyDataSource) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)).andCommandKey(HystrixCommandKey.Factory.asKey(groupKey)));

        this.pjp = pjp;
        this.emptyDataSource = emptyDataSource;
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
        if(emptyDataSource.isList()){
            return Collections.emptyList();
        }

        return null;
    }
}
