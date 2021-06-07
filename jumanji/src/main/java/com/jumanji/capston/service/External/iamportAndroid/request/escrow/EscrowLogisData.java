package com.jumanji.capston.service.external.iamportAndroid.request.escrow;

import com.google.gson.annotations.SerializedName;
import com.siot.IamportRestClient.request.escrow.EscrowLogisInvoiceData;
import com.siot.IamportRestClient.request.escrow.EscrowLogisPersonData;

public class EscrowLogisData {
	
	@SerializedName("sender")
	private EscrowLogisPersonData sender;
	
	@SerializedName("receiver")
	private EscrowLogisPersonData receiver;
	
	@SerializedName("logis")
	private EscrowLogisInvoiceData logis;
	
	public EscrowLogisData() {}

	public EscrowLogisData(EscrowLogisInvoiceData logis, EscrowLogisPersonData receiver) {
		this.receiver = receiver;
		this.logis = logis;
	}

	public EscrowLogisData(EscrowLogisInvoiceData logis, EscrowLogisPersonData receiver, EscrowLogisPersonData sender) {
		this.sender = sender;
		this.receiver = receiver;
		this.logis = logis;
	}

	public void setSender(EscrowLogisPersonData sender) {
		this.sender = sender;
	}

	public void setReceiver(EscrowLogisPersonData receiver) {
		this.receiver = receiver;
	}

	public void setLogis(EscrowLogisInvoiceData logis) {
		this.logis = logis;
	}

}
