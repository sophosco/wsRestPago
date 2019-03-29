package com.sophos.poc.pago.model;

import java.util.Date;

public class PaymentGateway {
	
	private String authorizationId;
	private String debitHolderName;
	private DocumentType documentType;
	private String nameGateway;
	private String entityCode;
	private Date applicationDate;
	private String document;
	private String phone;
	private String email;
	
	public PaymentGateway() {}

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	public String getDebitHolderName() {
		return debitHolderName;
	}

	public void setDebitHolderName(String debitHolderName) {
		this.debitHolderName = debitHolderName;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PaymentGateway(String authorizationId, String debitHolderName, DocumentType documentType, String nameGateway,
			String entityCode, Date applicationDate, String document, String phone, String email) {
		super();
		this.authorizationId = authorizationId;
		this.debitHolderName = debitHolderName;
		this.documentType = documentType;
		this.nameGateway = nameGateway;
		this.entityCode = entityCode;
		this.applicationDate = applicationDate;
		this.document = document;
		this.phone = phone;
		this.email = email;
	}
	
	
}
