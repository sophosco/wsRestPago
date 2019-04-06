package com.sophos.poc.pago.model;

import java.io.Serializable;

public class PaymentResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String approvalCode;
	private Double feeTax;
	private String rejectionCode;

	public PaymentResponse() {}


	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public Double getFeeTax() {
		return feeTax;
	}
	public void setFeeTax(Double feeTax) {
		this.feeTax = feeTax;
	}
	public String getRejectionCode() {
		return rejectionCode;
	}
	public void setRejectionCode(String rejectionCode) {
		this.rejectionCode = rejectionCode;
	}


	public PaymentResponse(String approvalCode, Double feeTax, String rejectionCode) {
		super();
		this.approvalCode = approvalCode;
		this.feeTax = feeTax;
		this.rejectionCode = rejectionCode;
	}

}
