package com.qingqing.test.config;

import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.test.hystrix.dataSource.SwitchableDateSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {
        "com.qingqing.test.dao.passport",
}, sqlSessionFactoryRef = PassportSourceDataConfig.SQL_SESSION_FACTORY)
public class PassportSourceDataConfig {

    public final static String DATA_SOURCE_NAME = "qq_passport";
    public final static String SQL_SESSION_FACTORY = DATA_SOURCE_NAME + "SqlSessionFactory";
    public final static String TX_MANAGER = DATA_SOURCE_NAME + "TransactionManager";

    private static String validation_query = "SELECT 1 FROM DUAL";

    private static final Logger logger = LoggerFactory.getLogger(PassportSourceDataConfig.class);

    @Value("${database.passport.url}")
    private String url;
    @Value("${database.passport.username}")
    private String username;
    @Value("${database.passport.password}")
    private String password;

    //按需添加
    @Value("${mybatis.config.path}")
    private String configPath;

    @Bean(name = DATA_SOURCE_NAME)
    public SwitchableDateSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxIdle(30);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(50);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(280); // sec
        dataSource.setMaxWait(30000);
        dataSource.setValidationQuery(validation_query);
        dataSource.setTestOnBorrow(true);

        return new SwitchableDateSource(dataSource);
    }

    @Bean(name = TX_MANAGER)
    public DataSourceTransactionManager getTransactionManager(@Qualifier(value=DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean( name = SQL_SESSION_FACTORY)
    public SqlSessionFactory getSqlSessionFactoryData(@Qualifier(value=DATA_SOURCE_NAME) DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configPath));
        bean.setDataSource(dataSource);

        try{
            return bean.getObject();
        } catch (Exception e) {
            logger.error("failed to create data sql session", e);
            throw new QingQingRuntimeException("failed to create data sql session", e);
        }
    }

}