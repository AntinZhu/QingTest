package ${basePackage}.config.redis;

import com.qingqing.userinfodp.config.DpClientConfig;
import com.qingqing.userinfodp.config.DpClientProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
public class UserInfoDpConfig {

    @Value("${"$"}{redis.userinfodpsvc.cluster.url}")
    private String userDpCluster;
    @Value("${"$"}{config.userinfodpsvc.host}")
    private String userDpHost;

    @Bean(name = DpClientConfig.QINGQING_USER_DP_CLIENT_PROPERTY_CONFIG_BEAN_NAME)
    public DpClientProperties dpClientProperties() {
        DpClientProperties dpClientProperties = new DpClientProperties();
        dpClientProperties.setClusterNodes(userDpCluster);
        dpClientProperties.setCommandTimeout(300);
        dpClientProperties.setCorrectDPHostName(userDpHost);
        dpClientProperties.setMaxAttempts(3);
        return dpClientProperties;
    }
}
