package com.crypto.trading.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.trading.models.CryptoModel;
import com.crypto.trading.services.CryptoService;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {
	
	@Autowired
	CryptoService cryptoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CryptoModel>> getCryptos() {
		return ResponseEntity.status(HttpStatus.OK).body(this.cryptoService.getCryptos());
	}
}
