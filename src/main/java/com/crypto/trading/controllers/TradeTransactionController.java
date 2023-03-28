package com.crypto.trading.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.trading.models.TradeModel;
import com.crypto.trading.models.TradeTransactionModel;
import com.crypto.trading.services.TradeTransactionService;

@RestController
@RequestMapping("/trades")
public class TradeTransactionController {
	
	@Autowired
	TradeTransactionService tradeTransactionService;
	
	@RequestMapping(path = "/traderId/{traderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TradeTransactionModel>> getTransactions(@PathVariable Long traderId) {
		return ResponseEntity.ok(this.tradeTransactionService.getTraderTransactions(traderId));
	}
	
	@RequestMapping(path = "/buy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TradeTransactionModel> buyCrypto(@RequestBody TradeModel trade) throws Exception {
		return ResponseEntity.ok(this.tradeTransactionService.buyCrypto(trade));
	}
	
	@RequestMapping(path = "/sell", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TradeTransactionModel> sellCrypto(@RequestBody TradeModel trade) throws Exception {
		return ResponseEntity.ok(this.tradeTransactionService.sellCrypto(trade));
	}
}
