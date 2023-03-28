package com.crypto.trading.converters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crypto.trading.entities.WalletBalance;
import com.crypto.trading.models.WalletBalanceModel;
import com.crypto.trading.utils.StreamUtils;

public class WalletBalanceConverter {
	
	public final static WalletBalanceModel fromEntity(WalletBalance entity) {
		return Optional.ofNullable(entity)
				.map(item -> WalletBalanceModel.builder()
						.cryptoAmount(item.getCryptoAmount())
						.cryptoSymbol(item.getCryptoSymbol())
						.id(item.getId())
						.traderId(item.getTraderId())
						.build())
				.orElse(null);
	}

	public static List<WalletBalanceModel> fromEntities(List<WalletBalance> entities) {
		return StreamUtils.safeStream(entities)
				.map(entity -> fromEntity(entity))
				.collect(Collectors.toList());
	}
}
