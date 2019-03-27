package com.sophos.poc.pago.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sophos.poc.pago.model.Payment;
import com.sophos.poc.pago.model.PaymentResponse;
import com.sophos.poc.pago.model.Status;

@Service
public class PaymentProcess {

	public ResponseEntity<Status> executePayment( Payment payment, boolean xIsError) {
		payment.setUuid(UUID.randomUUID().toString());
		payment.setCreateDate(new Date());
		PaymentResponse paymentResponse = new PaymentResponse();
		
		if(xIsError) {
			paymentResponse.setRejectionCode(System.currentTimeMillis()+"".lastIndexOf(6)+"");
			Status status = new Status("100", "AXS100", "ERROR: Fondos Insuficientes", paymentResponse);
			return new ResponseEntity<>(status, HttpStatus.PARTIAL_CONTENT);		
		}else {
			paymentResponse.setApprovalCode(System.currentTimeMillis()+"".lastIndexOf(4)+"");
			paymentResponse.setFeeTax(new Double(3000));
			Status status = new Status("0", "", "Operacion Exitosa", paymentResponse);
			return new ResponseEntity<>(status, HttpStatus.OK);		
		}
	}
}
