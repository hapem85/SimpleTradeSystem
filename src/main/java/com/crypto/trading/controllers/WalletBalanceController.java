package com.crypto.trading.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.trading.models.WalletBalanceModel;
import com.crypto.trading.services.WalletBalanceService;

@RestController
@RequestMapping("/wallet_balances")
public class WalletBalanceController {
	
	@Autowired
	WalletBalanceService walletBalanceService;
	
	@RequestMapping(path = "/trader/{traderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WalletBalanceModel>> getWalletBalancesByTraderId(@PathVariable Long traderId) {
		return ResponseEntity.status(HttpStatus.OK).body(this.walletBalanceService.getWalletBalancesByTraderId(traderId));
	}
}
