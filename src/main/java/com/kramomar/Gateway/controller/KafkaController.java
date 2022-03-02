package com.kramomar.Gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kramomar.Gateway.entity.Payment;
import com.kramomar.Gateway.repository.PaymentRepository;
import com.kramomar.Gateway.service.KafkaProducer;
import com.kramomar.Gateway.utils.Topic;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class KafkaController {

	private final KafkaProducer kafkaProducer;
	
	private final PaymentRepository paymentRepository;
	
	@GetMapping("/param/amount/{amount}/numberPhoneOrigin/{numberPhoneOrigin}/numberPhoneDestination/{numberPhoneDestination}")
	public String SendPayment(@PathVariable double amount,@PathVariable String numberPhoneOrigin,@PathVariable String numberPhoneDestination) {
		return kafkaProducer.SendPayment(amount,numberPhoneOrigin, numberPhoneDestination);
	}
	
	
	@GetMapping("/getall")
	public Flux<Payment> all() {
	return paymentRepository.findall();
	}
	    
	 
	@PostMapping
	Mono<Payment> createUser(@RequestBody Payment payment) {
	return paymentRepository.save(payment);
	}
	
}