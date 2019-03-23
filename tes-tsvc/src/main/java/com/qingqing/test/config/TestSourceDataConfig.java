package com.qingqing.test.config;

import com.google.common.collect.Lists;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.test.masterslave.QingMasterSlaveDataSource;
import io.shardingjdbc.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithmType;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.qingqing.test.dao.test",
}, sqlSessionFactoryRef = TestSourceDataConfig.SQL_SESSION_FACTORY)
public class TestSourceDataConfig {

    public final static String DATA_SOURCE_NAME = "qq_test";
    public final static String SQL_SESSION_FACTORY = DATA_SOURCE_NAME + "SqlSessionFactory";
    public final static String TX_MANAGER = DATA_SOURCE_NAME + "TransactionManager";

    private static String validation_query = "SELECT 1 FROM DUAL";

    private static final Logger logger = LoggerFactory.getLogger(TestSourceDataConfig.class);

    @Value("${database.test.url}")
    private String url;
    @Value("${database.test.username}")
    private String username;
    @Value("${database.test.password}")
    private String password;

    //按需添加
    @Value("${mybatis.config.path}")
    private String configPath;

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource getDataSource(@Qualifier(BackupSourceDataConfig.DATA_SOURCE_NAME) DataSource slaveDataSource ) throws SQLException {
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

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DATA_SOURCE_NAME, dataSource);
        dataSourceMap.put(BackupSourceDataConfig.DATA_SOURCE_NAME, slaveDataSource);

        MasterSlaveRuleConfiguration rule = new MasterSlaveRuleConfiguration();
        rule.setMasterDataSourceName(DATA_SOURCE_NAME);
        rule.setSlaveDataSourceNames(Lists.<String>newArrayList(BackupSourceDataConfig.DATA_SOURCE_NAME));
        rule.setLoadBalanceAlgorithmType(MasterSlaveLoadBalanceAlgorithmType.ROUND_ROBIN);
        rule.setName(DATA_SOURCE_NAME + "_master_slave");

        return new QingMasterSlaveDataSource(rule.build(dataSourceMap), Collections.<String, Object>emptyMap());
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