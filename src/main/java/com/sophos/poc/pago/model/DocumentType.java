package com.sophos.poc.pago.model;

import java.io.Serializable;

public class DocumentType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DocumentType(String value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	
	public DocumentType() {}
	
	

}
