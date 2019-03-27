package com.sophos.poc.model;

public class PaymentResponse {
	
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

}
