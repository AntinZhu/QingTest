package com.qingqing.test.hystrix.inter;

import com.qingqing.common.exception.ErrorCodeInterface;

/**
 * Created by zhujianxing on 2019/8/14.
 */
public class QingFusingStrategyHolder {

    private static ThreadLocal<QingFusingStrategyBean> fusingStrategyBean = new ThreadLocal<>();

    public static <T> void setFusingValue(IQingFusingValue<T> fusingValue){
        if(fusingValue == null){
            throw new NullPointerException("fusingValue cannot be null");
        }

        fusingStrategyBean.set(new QingFusingStrategyBean(QingFusingStrategy.demotion, fusingValue, null));
    }

    public static void setErrorCode(ErrorCodeInterface errorCode){
        if(errorCode == null){
            throw new NullPointerException("errorCode cannot be null");
        }

        fusingStrategyBean.set(new QingFusingStrategyBean(QingFusingStrategy.error_code, null, errorCode));
    }

    public static <T> QingFusingStrategyBean<T> getQingFusingStrategyBean(){
        return fusingStrategyBean.get();
    }

    public static void clear(){
        fusingStrategyBean.remove();;
    }

    public static class QingFusingStrategyBean<T>{
        private final QingFusingStrategy fusingMode;
        private final IQingFusingValue<T> fusingValue;
        private final ErrorCodeInterface errorCodeInterface;

        public QingFusingStrategyBean(QingFusingStrategy fusingMode, IQingFusingValue<T> fusingValue, ErrorCodeInterface errorCodeInterface) {
            this.fusingMode = fusingMode;
            this.fusingValue = fusingValue;
            this.errorCodeInterface = errorCodeInterface;
        }

        public QingFusingStrategy getFusingMode() {
            return fusingMode;
        }

        public IQingFusingValue<T> getFusingValue() {
            return fusingValue;
        }

        public ErrorCodeInterface getErrorCodeInterface() {
            return errorCodeInterface;
        }
    }
}
