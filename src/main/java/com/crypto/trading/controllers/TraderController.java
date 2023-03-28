package com.crypto.trading.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.trading.models.TraderModel;
import com.crypto.trading.services.TraderService;

@RestController
@RequestMapping("/traders")
public class TraderController {
	
	TraderService traderService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TraderModel>> getTraders() {
		return ResponseEntity.ok(traderService.getTraders());
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TraderModel> getTraderById(@PathVariable("id") Long traderId) throws Exception {
		TraderModel trader = this.traderService.getTraderById(traderId);
		return ResponseEntity.ok(trader);
	}
}
