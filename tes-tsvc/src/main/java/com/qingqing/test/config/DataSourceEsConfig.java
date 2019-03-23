package com.qingqing.test.config;

import com.alibaba.druid.pool.ElasticSearchDruidDataSource;
import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.common.util.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangzirong on 9/26/2017.
 */
@Configuration
@MapperScan(basePackages = {
        "com.qingqing.test.dao.es",
}, sqlSessionFactoryRef = DataSourceEsConfig.SQL_SESSION_FACTORY)
public class DataSourceEsConfig {

    public final static String DATA_SOURCE_NAME = "elasticSearchDruidDataSource";
    public final static String SQL_SESSION_FACTORY = "sqlElasticsearchSessionFactory";
    public final static List<String> XML_PATHS = new ArrayList<>(3);
    static {
        XML_PATHS.add("classpath:com/qingqing/test/dao/*.xml");
    }

    private static final Logger logger = LoggerFactory.getLogger(DataSourceEsConfig.class);

    @Value("${elasticsearch.url:result:elasticsearch://172.20.13.216:9300/elasticsearch}")
    private String url;

    //按需添加
    @Value("${mybatis.config.path}")
    private String configPath;

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource getDataSource() {
        ElasticSearchDruidDataSource druidDataSource = new ElasticSearchDruidDataSource();
        druidDataSource.setInitialSize(1);
        druidDataSource.setUrl("result:elasticsearch://172.20.13.216:9300/elasticsearch");
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(15);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(280);// second
        druidDataSource.setMaxWait(30000);

        druidDataSource.setInitialSize(1);
        return druidDataSource;
    }

//    @Bean
//    public DataSourceHealthIndicator getDataSourceHealthIndicator(@Qualifier(value=DATA_SOURCE_NAME) DataSource dataSource) {
//        return MybatisDataSourceConfigHelper.getDataSourceHealthIndicator(dataSource);
//    }

    @Bean( name = SQL_SESSION_FACTORY)
    public SqlSessionFactory getSqlSessionFactoryData(@Qualifier(value=DATA_SOURCE_NAME) DataSource dataSource) {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            List<Resource> resources = new ArrayList();
            Iterator i$ = XML_PATHS.iterator();

            while(i$.hasNext()) {
                String xmlPath = (String)i$.next();
                Resource[] t = (new PathMatchingResourcePatternResolver()).getResources(xmlPath);
                resources.addAll(Arrays.asList(t));
            }

            if(!resources.isEmpty()) {
                bean.setMapperLocations((Resource[])resources.toArray(new Resource[resources.size()]));
            }

            if(!StringUtils.isEmpty(configPath)) {
                bean.setConfigLocation((new PathMatchingResourcePatternResolver()).getResource(configPath));
            }

            bean.setDataSource(dataSource);
            return bean.getObject();
        } catch (Exception var8) {
            logger.error("failed to create data sql session", var8);
            throw new QingQingRuntimeException("failed to create data sql session", var8);
        }
    }

}
