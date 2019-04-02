package com.sophos.poc.pago.controller.client;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.poc.pago.model.Payment;
import com.sophos.poc.pago.model.Status;
import com.sophos.poc.pago.model.audit.Accion;

@EnableAsync
@Service
public class AuditClient {

	private static final Logger logger = LogManager.getLogger(AuditClient.class);

	@Async
	public ResponseEntity<Status> saveAudit(
				String IdSesion,
				String IdUsuario,
				String TipoAccion,
				String DescripcionAccion, 
				String ModuloAplicacion,
				String IdProducto,
				String IdCategoria,
				boolean xHaveToken,
				Payment Payment
			) throws JsonProcessingException{
		
		RestTemplate restTemplate = new RestTemplate(); 
		ObjectMapper obj = new ObjectMapper();
		

		try {
			 HttpHeaders headers = new HttpHeaders();
			 headers.set("X-RqUID", UUID.randomUUID().toString());
			 headers.set("X-Channel", "wsRestPago");
			 headers.set("X-IPAddr", "192.169.1.1");
			 headers.set("X-Sesion", IdSesion);
			 headers.set("X-haveToken", xHaveToken+"");
			 headers.set("Content-Type", "application/json");
			 
			 Accion accion = new Accion();
			 accion.setDescripcionAccion(DescripcionAccion);
			 OffsetDateTime offsetDate = OffsetDateTime.now();
			 accion.setFechaCreacion(offsetDate);
			 accion.setIdCategoria(IdCategoria);
			 accion.setIdProducto(IdProducto);
			 accion.setIdSesion(IdSesion);
			 accion.setIdUsuario(IdUsuario);
			 accion.setMessageData(Base64.encodeBase64String(obj.writeValueAsString(Payment).getBytes()));
			 accion.setModuloAplicacion(ModuloAplicacion);
			 accion.setTipoAccion(TipoAccion);
			 
			 HttpEntity<Accion> entity = new HttpEntity<Accion>(accion, headers);
			
			 logger.info("Request Audit: "+obj.writeValueAsString(entity));
			 
			 ResponseEntity<String> response =  restTemplate.exchange(
								System.getenv("POC_SERVICE_AUDIT_VALIDATE"),
								HttpMethod.POST,
								entity,
								String.class);
			 
			 logger.info("Response Audit ["+ response.getStatusCode() +"]: ", response.getBody());
		}catch(Exception e) {
			logger.error("Ocurrio un error al registrar auditoria de Pago", e);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
