package com.sophos.poc.model;

import java.util.Date;

public class Payment {
	
	private String uuid;
	private Date createDate;
	private CreditCard creditCard;
	private PaymentGateway paymentGateway;
	private CustomerPortfolio customerPortfolio;
	private Double ammount;
	
	public Payment() {}
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public PaymentGateway getPaymentGateway() {
		return paymentGateway;
	}
	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	public CustomerPortfolio getCustomerPortfolio() {
		return customerPortfolio;
	}
	public void setCustomerPortfolio(CustomerPortfolio customerPortfolio) {
		this.customerPortfolio = customerPortfolio;
	}
	
}
	
	
