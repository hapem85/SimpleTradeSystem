package com.crypto.trading.converters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crypto.trading.entities.Crypto;
import com.crypto.trading.models.BinanceTicker;
import com.crypto.trading.models.CryptoModel;
import com.crypto.trading.models.HuobiTicker;
import com.crypto.trading.utils.StreamUtils;

public class CryptoConverter {
	public static Crypto toCrypto(BinanceTicker ticker, String source) {
		float bidPrice = Float.parseFloat(ticker.getBidPrice());
		float askPrice = Float.parseFloat(ticker.getAskPrice());
		
		return Crypto.builder()
				.askPrice(askPrice)
				.bidPrice(bidPrice)
				.symbol(ticker.getSymbol().toUpperCase())
				.bidSource(source)
				.askSource(source)
				.build();
	}
	
	public static Crypto toCrypto(HuobiTicker ticker, String source) {
		return Crypto.builder()
				.askPrice(ticker.getAsk())
				.bidPrice(ticker.getBid())
				.symbol(ticker.getSymbol().toUpperCase())
				.bidSource(source)
				.askSource(source)
				.build();
	}
	
	public final static CryptoModel fromEntity(Crypto entity) {
		return Optional.ofNullable(entity)
				.map(item -> CryptoModel.builder()
						.id(item.getId())
						.askPrice(item.getAskPrice())
						.askSource(item.getAskSource())
						.bidPrice(item.getBidPrice())
						.bidSource(item.getBidSource())
						.symbol(item.getSymbol())
						.build())
				.orElse(null);
	}

	public static List<CryptoModel> fromEntities(List<Crypto> entities) {
		return StreamUtils.safeStream(entities)
				.map(entity -> fromEntity(entity))
				.collect(Collectors.toList());
	}
}
