package com.sophos.poc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.poc.controller.PaymentController;
import com.sophos.poc.controller.PaymentProcess;
import com.sophos.poc.controller.client.SecurityClient;
import com.sophos.poc.model.Payment;
import com.sophos.poc.model.Status;

@RunWith(SpringJUnit4ClassRunner.class)
public class WsRestPagoApplicationTests {

	@Mock
	private SecurityClient securityClient;
	
	@Mock
	private PaymentProcess paymentProcess;
	
	@InjectMocks
	private PaymentController paymentController;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		MockMvcBuilders.standaloneSetup(paymentController).build();
	}

	@Test
	public void paymentController_OK() throws Exception {
		Payment payment = new Payment();
		String xChannel = "SOPHOS";
		String xIPAddr = "192.168.1.1";
		boolean xIsError = false;
		String xSesion  = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJqd3RJZCI6IjExNTUzNjM2MDY5OTIxIiwiaWF0IjoxNTUzNjM2MDY5LCJleHAiOjE1NTM2MzY0ODksImF1ZCI6IkFWQUwiLCJpc3MiOiJQT0NBVkFMIiwic3ViIjoiU2Vzc2lvblRva2VuIn0.BZQyw-fqRiOikH4GgVJjyYNusEnS3bLD0E4MLuspu2w9mg1lo0VGTKCIzSEFPGteX6QjoKxhKj8dBYgnANF-Jw";
		boolean xHaveToken = true;
		
		when(securityClient.verifyJwtToken(xSesion)).thenReturn(new ResponseEntity<Status>(HttpStatus.OK));
		Status status = new Status("0", "Operacion Exitosa", "", null);
		ResponseEntity<Status> responseEntity = new ResponseEntity<Status>(status, HttpStatus.OK);
		when(paymentProcess.executePayment(payment, xIsError)).thenReturn(responseEntity);

		ResponseEntity<Status> statusResponse = paymentController.addPayment(
				UUID.randomUUID().toString(), 
				xChannel, 
				xIPAddr,
				xSesion,
				xHaveToken,
				xIsError,
				payment
			);
		
		assertEquals(statusResponse.getBody().getCode(), "0");
		assertEquals(statusResponse.getBody().getMessage(), "Operacion Exitosa");
		assertEquals(statusResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void paymentController_OK_Error() throws Exception {
		Payment payment = new Payment();
		String xChannel = "SOPHOS";
		String xIPAddr = "192.168.1.1";
		boolean xIsError = true;
		String xSesion  = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJqd3RJZCI6IjExNTUzNjM2MDY5OTIxIiwiaWF0IjoxNTUzNjM2MDY5LCJleHAiOjE1NTM2MzY0ODksImF1ZCI6IkFWQUwiLCJpc3MiOiJQT0NBVkFMIiwic3ViIjoiU2Vzc2lvblRva2VuIn0.BZQyw-fqRiOikH4GgVJjyYNusEnS3bLD0E4MLuspu2w9mg1lo0VGTKCIzSEFPGteX6QjoKxhKj8dBYgnANF-Jw";
		boolean xHaveToken = true;
		
		when(securityClient.verifyJwtToken(xSesion)).thenReturn(new ResponseEntity<Status>(HttpStatus.OK));
		Status status = new Status("100", "AXS100", "ERROR: Fondos Insuficientes",  null);
		ResponseEntity<Status> responseEntity = new ResponseEntity<Status>(status, HttpStatus.PARTIAL_CONTENT);
		when(paymentProcess.executePayment(payment, xIsError)).thenReturn(responseEntity);

		ResponseEntity<Status> statusResponse = paymentController.addPayment(
				UUID.randomUUID().toString(), 
				xChannel, 
				xIPAddr,
				xSesion,
				xHaveToken,
				xIsError,
				payment
			);
		
		assertEquals(statusResponse.getBody().getCode(), "100");
		assertEquals(statusResponse.getStatusCode(), HttpStatus.PARTIAL_CONTENT);
	}
	
}
