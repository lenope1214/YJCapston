//package com.jumanji.capston.service.External.iamport.request;
//
//import java.math.BigDecimal;
//
//import com.google.gson.annotations.SerializedName;
//import com.siot.IamportRestClient.request.CardInfo;
//
//public class OnetimePaymentData {
//
//	@SerializedName("merchant_uid")
//	private String merchant_uid;
//
//	@SerializedName("amount")
//	private BigDecimal amount;
//
//	@SerializedName("vat")
//	private BigDecimal vat;
//
//	@SerializedName("customer_uid")
//	private String customer_uid;
//
//	@SerializedName("name")
//	private String name;
//
//	@SerializedName("buyer_name")
//	private String buyer_name;
//
//	@SerializedName("buyer_email")
//	private String buyer_email;
//
//	@SerializedName("buyer_tel")
//	private String buyer_tel;
//
//	@SerializedName("buyer_addr")
//	private String buyer_addr;
//
//	@SerializedName("buyer_postcode")
//	private String buyer_postcode;
//
//	@SerializedName("card_quota")
//	private int card_quota;
//
//	@SerializedName("card_number")
//	protected String card_number;
//
//	@SerializedName("expiry")
//	protected String expiry;
//
//	@SerializedName("birth")
//	protected String birth;
//
//	@SerializedName("pwd_2digit")
//	protected String pwd_2digit;
//
//	public OnetimePaymentData(String merchant_uid, BigDecimal amount, CardInfo card) {
//		this.merchant_uid = merchant_uid;
//		this.amount = amount;
//		this.card_number = card.card_number;
//		this.expiry = card.expiry;
//		this.birth = card.birth;
//		this.pwd_2digit = card.pwd_2digit;
//	}
//
//	public void setVat(BigDecimal vat) {
//		this.vat = vat;
//	}
//
//	public void setCustomer_uid(String customer_uid) {
//		this.customer_uid = customer_uid;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setBuyerName(String buyer_name) {
//		this.buyer_name = buyer_name;
//	}
//
//	public void setBuyerEmail(String buyer_email) {
//		this.buyer_email = buyer_email;
//	}
//
//	public void setBuyerTel(String buyer_tel) {
//		this.buyer_tel = buyer_tel;
//	}
//
//	public void setBuyerAddr(String buyer_addr) {
//		this.buyer_addr = buyer_addr;
//	}
//
//	public void setBuyerPostcode(String buyer_postcode) {
//		this.buyer_postcode = buyer_postcode;
//	}
//
//	public void setCardQuota(int card_quota) {
//		this.card_quota = card_quota;
//	}
//
//}
