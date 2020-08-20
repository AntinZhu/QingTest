package ${basePackage};

import com.qingqing.common.util.LogUtils;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.common.web.util.GuidUtil;
import com.qingqing.springboot.config.SpringApplicationBase;
import com.qingqing.springboot.druid.EnableDruidMetric;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.qingqing.springboot.listener.ApplicationFailedListener;

import java.util.Date;

/**
* Created by ${user!'test-api'} on ${date}
*/
@SpringBootApplication
@EnableAutoConfiguration(exclude={
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                MybatisAutoConfiguration.class,
                RedisAutoConfiguration.class,
                WebMvcAutoConfiguration.class,
                RedisRepositoriesAutoConfiguration.class,
                DispatcherServletAutoConfiguration.class,
                ErrorMvcAutoConfiguration.class,
                RabbitAutoConfiguration.class,
                KafkaAutoConfiguration.class,
})
@ComponentScan(basePackages = {"${basePackage}", "com.qingqing.springboot.config"${svcOtherScan!''}})
@EnableDruidMetric
@EnableTransactionManagement(proxyTargetClass = true)
public class SvcApplication extends SpringApplicationBase {
    private static final Logger logger = LoggerFactory.getLogger(SvcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SvcApplication.class, args);
    }

    static {
        try {
            GuidUtil.addGuid("start_server-" + TimeUtil.dateToString(new Date(), TimeUtil.TIME_IN_DETAIL_STRING_FORMAT), false);
            logger.info("mdc initing....");
            LogUtils.initGlobalMDC(); //-DisThreadContextMapInheritable=true
            logger.info("mdc init succ");
        } catch (Exception e) {
            logger.error("failed to init mdc", e);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.listeners(new ApplicationFailedListener());
        return builder.sources(SvcApplication.class);
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8080);
        factory.setContextPath("/${svcName}");
    }
}
