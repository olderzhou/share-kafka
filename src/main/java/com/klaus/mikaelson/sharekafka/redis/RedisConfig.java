package com.klaus.mikaelson.sharekafka.redis;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>
 * redis缓存配置
 * </p>
 * Created by zhezhiyong@163.com on 2017/9/21.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Primary
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		// 缓存配置对象
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

		redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L)) // 设置缓存的默认超时时间：30分钟
				.disableCachingNullValues() // 如果是空值，不缓存
				.prefixKeysWith(":")
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer())) // 设置key序列化器
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer()))); // 设置value序列化器

		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(keySerializer());
		redisTemplate.setHashKeySerializer(keySerializer());
		redisTemplate.setValueSerializer(valueSerializer());
		redisTemplate.setHashValueSerializer(valueSerializer());

		return redisTemplate;
	}

	private RedisSerializer<String> keySerializer() {
		return new StringRedisSerializer();
	}

	private RedisSerializer<Object> valueSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

}