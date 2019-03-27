package com.sophos.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.poc.controller.client.AuditClient;
import com.sophos.poc.controller.client.SecurityClient;
import com.sophos.poc.model.Payment;
import com.sophos.poc.model.Status;


@RestController
public class PaymentController {
	
	@Autowired
	private SecurityClient securityClient;
	
	@Autowired
	private PaymentProcess paymentProcess;
	
	@Autowired
	private AuditClient auditClient;
	
	
	public PaymentController(SecurityClient securityClient, PaymentProcess paymentProcess, AuditClient auditClient) {
		this.securityClient = securityClient;
		this.paymentProcess = paymentProcess;
		this.auditClient = auditClient;
	}

	@RequestMapping(value = "/api/payment/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Status> addPayment(
			@RequestHeader(value = "X-RqUID", required = true) String xRqUID,
			@RequestHeader(value = "X-Channel", required = true) String xChannel,
			@RequestHeader(value = "X-IPAddr", required = true) String xIPAddr,
			@RequestHeader(value = "X-Sesion", required = true) String xSesion,
			@RequestHeader(value = "X-haveToken", required = false, defaultValue = "true") boolean xHaveToken,
			@RequestHeader(value = "X-isError", required = false, defaultValue = "false") boolean xIsError,
			@RequestBody Payment payment) {

		try {
			
			if((xSesion == null || xSesion.isEmpty()) || (xHaveToken && HttpStatus.UNAUTHORIZED.equals(securityClient.verifyJwtToken(xSesion).getStatusCode()))) {
				Status status = new Status("500","El token no es valido o ya expiro. Intente mas tarde", "ERROR Ocurrio una exception inesperada", null);
				return new ResponseEntity<>(status, HttpStatus.UNAUTHORIZED);
			}
			if(payment == null) {
				Status status = new Status("500", "ERROR Ocurrio una exception inesperada", "Objecto Orders es <NULL>", null);
				return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			if(xRqUID == null || xChannel == null || xIPAddr == null ) {
				Status status = new Status("500", "ERROR Ocurrio una exception inesperada", "Valor <NULL> en alguna cabecera obligatorio (X-RqUID X-Channel X-IPAddr X-Sesion)", null);
				return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			auditClient.saveAudit(
						payment.getIdSesion(),
						payment.getIdUsuario(),
						"Realizar Orden",
						"Realiza el proceso de Pago posterior a la confirmación del usuario",
						"Modulo de Pago",
						null,
						null,
						payment
			);
			return paymentProcess.executePayment(payment, xIsError);

		} catch (Exception e) {
			Status status = new Status("500", e.getMessage(), "ERROR Ocurrio una exception inesperada", null);
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}

}
