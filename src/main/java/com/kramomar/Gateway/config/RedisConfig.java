package com.kramomar.Gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.kramomar.Gateway.entity.Payment;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
	    RedisStandaloneConfiguration redisStandaloneConfig = new RedisStandaloneConfiguration();
	    redisStandaloneConfig.setHostName("localhost");
	    redisStandaloneConfig.setPort(6379);
	    //redisStandaloneConfig.setPassword(redisPassword);
	    return new LettuceConnectionFactory(redisStandaloneConfig);
	}

	@Bean
	public ReactiveRedisOperations<String, Payment> redisOperations(LettuceConnectionFactory connectionFactory) {
	     RedisSerializationContext<String, Payment> serializationContext = RedisSerializationContext
	             .<String, Payment>newSerializationContext(new StringRedisSerializer())
	             .key(new StringRedisSerializer())
	             .value(new GenericToStringSerializer<>(Payment.class))
	             .hashKey(new StringRedisSerializer())
	             .hashValue(new GenericJackson2JsonRedisSerializer())
	             .build();
	     return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
	 }
}
