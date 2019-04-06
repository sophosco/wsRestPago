package com.sophos.poc.pago.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String uuid;
	@JsonProperty(value="idSession")
	private String idSesion;
	private String idUser;
	private Date createDate;
	@JsonProperty(value="cardCredit")
	private CreditCard creditCard;
	private PaymentGateway paymentGateway;
	@JsonProperty(value ="customerPortafolio")
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
	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}


	public String getIdUser() {
		return idUser;
	}


	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}


	public Payment(String uuid, String idSesion, String idUser, Date createDate, CreditCard creditCard,
			PaymentGateway paymentGateway, CustomerPortfolio customerPortfolio, Double ammount) {
		super();
		this.uuid = uuid;
		this.idSesion = idSesion;
		this.idUser = idUser;
		this.createDate = createDate;
		this.creditCard = creditCard;
		this.paymentGateway = paymentGateway;
		this.customerPortfolio = customerPortfolio;
		this.ammount = ammount;
	}


}
	
	
