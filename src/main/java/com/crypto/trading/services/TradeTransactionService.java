package com.crypto.trading.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.repositories.TradeTransactionRepository;

@Service
public class TradeTransactionService {

	@Autowired
	private TradeTransactionRepository tradeTransactionRepository;
}
