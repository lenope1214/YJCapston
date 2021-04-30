package com.jumanji.capston.service.External.iamport.response.payco;

import com.google.gson.annotations.SerializedName;

public class OrderStatus {

	@SerializedName("status")
	private String status;

	public String getStatus() {
		return status;
	}
	
}
