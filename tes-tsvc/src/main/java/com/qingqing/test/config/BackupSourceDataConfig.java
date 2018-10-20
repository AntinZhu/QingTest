package com.qingqing.test.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BackupSourceDataConfig {

    public final static String DATA_SOURCE_NAME = "qq_test_backup";

    private static String validation_query = "SELECT 1 FROM DUAL";

    @Value("${database.test_backup.url}")
    private String url;
    @Value("${database.test_backup.username}")
    private String username;
    @Value("${database.test_backup.password}")
    private String password;

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource getDataSource() {
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
        return dataSource;
    }
}