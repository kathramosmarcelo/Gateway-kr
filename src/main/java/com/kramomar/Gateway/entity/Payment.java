package com.kramomar.Gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

	private String id;
	private double amount;
	private String numberPhoneOrigin;
	private String numberPhoneDestination;
	private String date;
	
}
