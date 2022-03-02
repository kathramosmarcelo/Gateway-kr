package com.kramomar.Gateway.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kramomar.Gateway.entity.Payment;
import com.kramomar.Gateway.utils.Topic;

@Service
public class KafkaProducer {

	@Autowired
	public  KafkaTemplate<String, Payment> kafkaTemplate;
	
	public String SendPayment( double amount,String numberPhoneOrigin,String numberPhoneDestination) {
	    kafkaTemplate.send(Topic.INSERT_PAYMENT, new Payment("PAY-001",amount,numberPhoneOrigin,numberPhoneDestination,"02/03/2022"));
	    return "Connecting Successfully :D";
	}

	public Payment publishEventPayment(Payment payment){
	    kafkaTemplate.send(Topic.INSERT_PAYMENT,payment);
	    return payment;
	}
	
}

