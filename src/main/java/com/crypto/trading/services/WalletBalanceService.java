package com.crypto.trading.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.repositories.WalletBalanceRepository;

@Service
public class WalletBalanceService {

	@Autowired
	private WalletBalanceRepository walletBalanceRepository;
}
