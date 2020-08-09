package ${basePackage}.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
@EnableApolloConfig({"application", "common", "config", "sentry", "public","ops","security"})
public class ApolloConfig {
}
