package com.crypto.trading.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.converters.WalletBalanceConverter;
import com.crypto.trading.entities.WalletBalance;
import com.crypto.trading.models.WalletBalanceModel;
import com.crypto.trading.repositories.WalletBalanceRepository;
import com.crypto.trading.utils.StreamUtils;

@Service
public class WalletBalanceService {

	@Autowired
	private WalletBalanceRepository walletBalanceRepository;

	public List<WalletBalanceModel> getWalletBalancesByTraderId(Long traderId) {
		List<WalletBalance> entities = StreamUtils.toList(this.walletBalanceRepository.findByTraderId(traderId));
		return WalletBalanceConverter.fromEntities(entities);
	}
}
