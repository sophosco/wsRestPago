package com.sophos.poc.pago.model;

import java.util.Date;

public class CustomerPortfolio {
	
	private String authorizationId;
	private String entityCode;
	private String tokenAuthorization;
	private Date applicationDate;
	private String portafolio;
	
	public CustomerPortfolio() {}
	
	public String getAuthorizationId() {
		return authorizationId;
	}
	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}
	public String getEntityCode() {
		return entityCode;
	}
	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}
	public String getTokenAuthorization() {
		return tokenAuthorization;
	}
	public void setTokenAuthorization(String tokenAuthorization) {
		this.tokenAuthorization = tokenAuthorization;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getPortafolio() {
		return portafolio;
	}
	public void setPortafolio(String portafolio) {
		this.portafolio = portafolio;
	}
}
