package com.jumanji.capston.service.External.iamport.response;

import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.siot.IamportRestClient.response.Balance;

public class PaymentBalanceEntry {

	@SerializedName("cash_receipt")
    com.siot.IamportRestClient.response.Balance cash_receipt;
	
	@SerializedName("primary")
    com.siot.IamportRestClient.response.Balance primary;
	
	@SerializedName("secondary")
    com.siot.IamportRestClient.response.Balance secondary;
	
	@SerializedName("discount")
    com.siot.IamportRestClient.response.Balance discount;
	
	@SerializedName("created")
	Date created;
	
	public PaymentBalanceEntry(com.siot.IamportRestClient.response.Balance cash_receipt, com.siot.IamportRestClient.response.Balance primary, com.siot.IamportRestClient.response.Balance secondary, com.siot.IamportRestClient.response.Balance discount,
                               Date created) {
		super();
		this.cash_receipt = cash_receipt;
		this.primary = primary;
		this.secondary = secondary;
		this.discount = discount;
		this.created = created;
	}

	public com.siot.IamportRestClient.response.Balance getCashReceipt() {
		return cash_receipt;
	}

	public com.siot.IamportRestClient.response.Balance getPrimary() {
		return primary;
	}

	public com.siot.IamportRestClient.response.Balance getSecondary() {
		return secondary;
	}

	public Balance getDiscount() {
		return discount;
	}
	
	public Date getCreated() {
		return created;
	}
	
}
