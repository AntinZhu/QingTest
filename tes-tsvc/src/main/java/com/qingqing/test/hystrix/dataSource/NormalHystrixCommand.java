package com.qingqing.test.hystrix.dataSource;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhujianxing on 2018/10/16.
 */
public class NormalHystrixCommand extends HystrixCommand<Object> {
    private static final Logger logger = LoggerFactory.getLogger(NormalHystrixCommand.class);

    private final ProceedingJoinPoint pjp;

    public NormalHystrixCommand(String groupKey, ProceedingJoinPoint pjp) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey)).andCommandKey(HystrixCommandKey.Factory.asKey(groupKey)));
        this.pjp = pjp;
    }

    @Override
    protected Object run() throws Exception {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw (Exception)throwable;
        }
    }
}
