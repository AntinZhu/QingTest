package com.qingqing.test.config;

import com.qingqing.test.service.SessionRedisService;
import com.qingqing.test.service.impl.SessionRedisServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by yangzirong on 9/26/2017.
 */
@Configuration
public class SessionRedisConfig {

    private final static String SESSION_STRINGREDIS_TEMPLATE = "sessionStringRedisTemplate";
    private final static String SESSION_JEDIS_CONNECTION_FACTORY = "sessionJedisConnectionFactory";
    private final static String SESSION_REDISSERVICE = "sessionRedisService";

    @Bean(name = SESSION_REDISSERVICE)
    public SessionRedisService sessionRedisService(
            @Qualifier(value = SESSION_STRINGREDIS_TEMPLATE) StringRedisTemplate template
    ){
        SessionRedisServiceImpl cacheRedisService = new SessionRedisServiceImpl();
        cacheRedisService.setRedisTemplate(template);

        return cacheRedisService;
    }

    public final static String COMMON_JEDISPOOL_CONFIG = "commonJedisPoolConfig";
    public final static Integer redisTimeout = 2000;

    @Bean(name = COMMON_JEDISPOOL_CONFIG)
    public JedisPoolConfig commonJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }


    @Bean(name = SESSION_JEDIS_CONNECTION_FACTORY)
    public JedisConnectionFactory sessionJedisConnectionFactory(
            @Qualifier(value = COMMON_JEDISPOOL_CONFIG) JedisPoolConfig jedisPoolConfig,
            @Value("${session.redis.host}") String host,
            @Value("${session.redis.port}") Integer port,
            @Value("${session.redis.password}") String password
            ){
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
        factory.setUsePool(true);
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setTimeout(redisTimeout);
        return factory;
    }


    @Bean(name = SESSION_STRINGREDIS_TEMPLATE)
    public StringRedisTemplate sessionStringRedisTemplate(
            @Qualifier(value = SESSION_JEDIS_CONNECTION_FACTORY) JedisConnectionFactory connectionFactory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
