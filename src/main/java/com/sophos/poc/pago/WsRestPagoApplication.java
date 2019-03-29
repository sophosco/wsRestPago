package com.sophos.poc.pago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WsRestPagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsRestPagoApplication.class, args);
	}
	

}
