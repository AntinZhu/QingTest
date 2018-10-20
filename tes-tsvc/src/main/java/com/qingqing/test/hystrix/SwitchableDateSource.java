package com.qingqing.test.hystrix;

import com.qingqing.common.exception.QingQingRuntimeException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DelegatingDataSource;

import javax.sql.DataSource;

/**
 * Created by zhujianxing on 2018/10/16.
 */
public class SwitchableDateSource extends DelegatingDataSource implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public SwitchableDateSource(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public DataSource getTargetDataSource() {
        String dateSourceBeanName = BackupDateSourceHystrixCommand.getBindDataSourceBeanName();
        if(dateSourceBeanName != null){
            DataSource dataSource =  applicationContext.getBean(dateSourceBeanName, DataSource.class);
            if(dataSource == null){
                throw new QingQingRuntimeException("unknown dataSource for beanName:" +dateSourceBeanName);
            }

            return dataSource;
        }
        return super.getTargetDataSource();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
