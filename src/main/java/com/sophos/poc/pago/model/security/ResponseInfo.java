package com.sophos.poc.pago.model.security;

import java.io.Serializable;
import java.util.Date;

public class ResponseInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String system;
	private Date responseDate;
	
	public ResponseInfo() {}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public ResponseInfo(String system, Date responseDate) {
		super();
		this.system = system;
		this.responseDate = responseDate;
	}
	
	

}
