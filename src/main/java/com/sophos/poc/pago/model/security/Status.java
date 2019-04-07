package com.sophos.poc.pago.model.security;

import java.io.Serializable;

public class Status implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	
	public Status() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	
}

