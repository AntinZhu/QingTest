package ${basePackage}.config.redis;

import com.qingqing.boot.actuator.base.annotations.ReadinessCheck;
import com.qingqing.boot.actuator.base.constants.Component;
import com.qingqing.common.redissvc.CacheRedisService;
import com.qingqing.common.redissvc.impl.BaseRedisService;
import com.qingqing.common.redissvc.impl.CacheRedisServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* Created by ${user!'test-api'} on ${date}
*/
@Configuration
public class SelfRedisConfig {

    @Value("${"$"}{redis.${svcName}.sentinel.url}")
    private String activityRedisSentinelUrls;
    @Value("${"$"}{redis.${svcName}.sentinel.master.name}")
    private String activityRedisSentinelMasterName;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxWaitMillis(3000);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    private static RedisSentinelConfiguration redisSentinelConfig(String redisName, String sentinelUrl) {
        return new RedisSentinelConfiguration(redisName, sentinelHostAndPorts(sentinelUrl));
    }

    private static Set<String> sentinelHostAndPorts(String sentinelUrl) {
        List<String> servers = Arrays.asList(sentinelUrl.split(","));
        return new HashSet<>(servers);
    }

    @Bean
    @ReadinessCheck(component = Component.REDIS)
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(redisSentinelConfig(activityRedisSentinelMasterName, activityRedisSentinelUrls), jedisPoolConfig);
        jedisConnectionFactory.setTimeout(2000);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        return new StringRedisTemplate(jedisConnectionFactory);
    }

    @Bean
    public CacheRedisService cacheRedisService(StringRedisTemplate redisTemplate) {
        CacheRedisServiceImpl cacheRedisService = new CacheRedisServiceImpl();
        initBaseRedisService(cacheRedisService, redisTemplate);
        return cacheRedisService;
    }

    private void initBaseRedisService(BaseRedisService cacheRedisService, StringRedisTemplate redisTemplate) {
        cacheRedisService.setRedisTemplate(redisTemplate);
    }
}
