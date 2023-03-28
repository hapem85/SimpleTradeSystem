package com.crypto.trading.converters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crypto.trading.entities.TradeTransaction;
import com.crypto.trading.models.TradeTransactionModel;
import com.crypto.trading.utils.StreamUtils;

public class TradeTransactionConverter {
	
	public final static TradeTransactionModel fromEntity(TradeTransaction entity) {
		return Optional.ofNullable(entity)
				.map(item -> TradeTransactionModel.builder()
						.id(item.getId())
						.creditedCryptoAmount(item.getCreditedCryptoAmount())
						.creditedCryptoName(item.getCreditedCryptoName())
						.debitedCryptoAmount(item.getDebitedCryptoAmount())
						.debitedCryptoName(item.getDebitedCryptoName())
						.fromSource(item.getFromSource())
						.traderId(item.getTraderId())
						.tradingDate(item.getTradingDate())
						.build())
				.orElse(null);
	}

	public static List<TradeTransactionModel> fromEntities(List<TradeTransaction> entities) {
		return StreamUtils.safeStream(entities)
				.map(entity -> fromEntity(entity))
				.collect(Collectors.toList());
	}
}
