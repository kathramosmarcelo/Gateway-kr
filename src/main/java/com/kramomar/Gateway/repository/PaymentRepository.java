package com.kramomar.Gateway.repository;

import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import com.kramomar.Gateway.entity.Payment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PaymentRepository {

	private final ReactiveRedisOperations<String, Payment> redisOperations;
	  private final ReactiveHashOperations<String,String, Payment> hashOperations;

	  public static final String KEY = "Payment";
	  public PaymentRepository(ReactiveRedisOperations<String, Payment> redisOperations) {
	    this.redisOperations = redisOperations;
	    this.hashOperations = redisOperations.opsForHash();
	  }
	  
	  public Flux<Payment> findall(){
	    return hashOperations.values(KEY);
	  }
	
	  
	  public Mono<Payment> save(Payment payment){
		return hashOperations.put(KEY, payment.getId(), payment).map(isSaved -> payment);
	  }
	  
	  public Mono<Payment> save_user(Payment payment) {
	    hashOperations.put(KEY, payment.getId(), payment);
	    return save(payment);  
      }	
}
