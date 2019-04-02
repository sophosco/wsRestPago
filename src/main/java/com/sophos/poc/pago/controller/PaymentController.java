package com.sophos.poc.pago.controller;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.poc.pago.controller.client.AuditClient;
import com.sophos.poc.pago.controller.client.SecurityClient;
import com.sophos.poc.pago.model.Payment;
import com.sophos.poc.pago.model.Status;


@RestController
public class PaymentController {
	
	@Autowired
	private SecurityClient securityClient; 
	
	@Autowired
	private PaymentProcess paymentProcess;
	
	@Autowired
	private AuditClient auditClient;
	
	private static final Logger logger = LogManager.getLogger(PaymentController.class);

	
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
			@RequestHeader(value = "X-HaveToken", required = false, defaultValue = "true") boolean xHaveToken,
			@RequestHeader(value = "X-isError", required = false, defaultValue = "false") boolean xIsError,
			@RequestBody Payment payment) {

		try {			
			ObjectMapper mapper = new ObjectMapper();
			
			logger.info("Headers: xSesion["+ xSesion +"] ");
			logger.info("Request: "+mapper.writeValueAsString(payment));
			String defaultError ="ERROR Ocurrio una exception inesperada";
			
			if((xSesion == null || xSesion.isEmpty()) || (xHaveToken && HttpStatus.UNAUTHORIZED.equals(securityClient.verifyJwtToken(xSesion).getStatusCode()))) {
				Status status = new Status("500","El token no es valido o ya expiro. Intente mas tarde", defaultError, null);
				ResponseEntity<Status> res = new ResponseEntity<>(status, HttpStatus.UNAUTHORIZED);
				logger.info("Response ["+ res.getStatusCode() +"] :"+mapper.writeValueAsString(res));
				return res;
			}
			if(payment == null) {
				Status status = new Status("500", defaultError, "Objecto Payment es <NULL>", null);
				ResponseEntity<Status> res = new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
				logger.info("Response ["+ res.getStatusCode() +"] :"+mapper.writeValueAsString(res));
				return res;
			}
			
			if(xRqUID == null || xChannel == null || xIPAddr == null ) {
				Status status = new Status("500", defaultError, "Valor <NULL> en alguna cabecera obligatorio (X-RqUID X-Channel X-IPAddr X-Sesion)", null);
				ResponseEntity<Status> res = new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
				logger.info("Response ["+ res.getStatusCode() +"] :"+mapper.writeValueAsString(res));
				return res;
			}
			
			
			payment.setUuid(UUID.randomUUID().toString());
			
			if(payment.getIdSesion() == null || payment.getIdSesion().isEmpty())
				payment.setIdSesion(UUID.randomUUID().toString());
			
			auditClient.saveAudit(
						xSesion,
						payment.getIdUser(),
						"Realizar Pago",
						"Realiza el proceso de Pago posterior a la confirmación del usuario",
						"Modulo de Pago",
						null,
						null,
						xHaveToken,
						payment
			);

			ResponseEntity<Status> res = paymentProcess.executePayment(payment, xIsError);
			logger.info("Response ["+ res.getStatusCode() +"] :"+mapper.writeValueAsString(res));
			return res;

		} catch (Exception e) {
			logger.error("Ocurrio una exception inesperada", e);
			Status status = new Status("500", e.getMessage(), "ERROR Ocurrio una exception inesperada", null);
			ResponseEntity<Status> response = new ResponseEntity<Status>(status, HttpStatus.INTERNAL_SERVER_ERROR);
			return response;		
		}
	}

}
