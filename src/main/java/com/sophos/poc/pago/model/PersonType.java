package com.sophos.poc.pago.model;

public class PersonType {
	private String id;
	private String value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public PersonType(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

}
