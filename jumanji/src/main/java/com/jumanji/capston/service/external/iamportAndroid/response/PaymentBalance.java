package com.jumanji.capston.service.external.iamportAndroid.response;

import java.math.BigDecimal;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.siot.IamportRestClient.response.Balance;
import com.siot.IamportRestClient.response.PaymentBalanceEntry;

public class PaymentBalance {

	@SerializedName("amount")
	BigDecimal amount;
	
	@SerializedName("cash_receipt")
    com.siot.IamportRestClient.response.Balance cash_receipt;
	
	@SerializedName("primary")
    com.siot.IamportRestClient.response.Balance primary;
	
	@SerializedName("secondary")
    com.siot.IamportRestClient.response.Balance secondary;
	
	@SerializedName("discount")
    com.siot.IamportRestClient.response.Balance discount;
	
	@SerializedName("histories")
	List<PaymentBalanceEntry> histories;

	public BigDecimal getAmount() {
		return amount;
	}

	public List<PaymentBalanceEntry> getHistories() {
		return histories;
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

}
