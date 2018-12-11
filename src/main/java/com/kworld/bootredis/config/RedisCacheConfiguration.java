package com.kworld.bootredis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisCacheConfiguration {
	
	@Value("${redis.host:localhost}")
	private String redisHost;
	
	@Value("${redis.port:6379}")
	private Integer redisPort;
	
	@Value("${redis.password:pwd}")
	private String redisPassword;
	
	@Bean("jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(this.redisHost, this.redisPort);
	    //redisStandaloneConfiguration.setPassword(this.redisPassword); Uncomment when required
	    return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
    RedisTemplate<Object, Object> redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {//Qualifier Used in case later if I need to add multiple factories pointing to different servers then it will be required
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }
	
	@Bean
	public CacheManager cacheManager(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
		return RedisCacheManagerBuilder
				.fromConnectionFactory(jedisConnectionFactory)
				.build();
	}
}
