package com.qingqing.api.teacher.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
* Created on ${date}
*/
@Configuration
@EnableApolloConfig({"application", "common", "config", "sentry"})
public class ApolloConfig {
}
