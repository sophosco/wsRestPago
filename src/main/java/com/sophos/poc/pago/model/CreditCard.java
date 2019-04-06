package com.sophos.poc.pago.model;

import java.io.Serializable;

public class CreditCard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cardHolderName;
	private String cardNumber;
	private String cvv;
	private String expiredMonth;
	private String expiredYear;
	
	
	public CreditCard() {}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpiredMonth() {
		return expiredMonth;
	}
	public void setExpiredMonth(String expiredMonth) {
		this.expiredMonth = expiredMonth;
	}
	public String getExpiredYear() {
		return expiredYear;
	}
	public void setExpiredYear(String expiredYear) {
		this.expiredYear = expiredYear;
	}

	public CreditCard(String cardHolderName, String cardNumber, String cvv, String expiredMonth, String expiredYear) {
		super();
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiredMonth = expiredMonth;
		this.expiredYear = expiredYear;
	}
}
