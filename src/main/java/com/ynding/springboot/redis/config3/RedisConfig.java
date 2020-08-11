package com.ynding.springboot.redis.config3;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置项.
 *
 * @author ynding
 * @version 1.0
 * @date 2020/8/8 15:30
 */
//@Configuration
//@EnableCaching
public class RedisConfig {


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        // JedisConnectionFactory配置hsot、database、password等参数
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("172.17.2.90");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("redis"));

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        // key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // value序列化方式采用RedisObjectSerializer
        template.setValueSerializer(new RedisObjectSerializer());

        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // hash的value序列化方式采用RedisObjectSerializer
        template.setHashValueSerializer(new RedisObjectSerializer());
        return template;
    }
}