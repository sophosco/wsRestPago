package com.sophos.poc.model;

import java.util.Date;

public class PaymentGateway {
	
	private String authorizationId;
	private String nameGateway;
	private String entityCode;
	private Date applicationDate;
	
	public PaymentGateway() {}
	
	public String getAuthorizationId() {
		return authorizationId;
	}
	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}
	public String getNameGateway() {
		return nameGateway;
	}
	public void setNameGateway(String nameGateway) {
		this.nameGateway = nameGateway;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	
}
