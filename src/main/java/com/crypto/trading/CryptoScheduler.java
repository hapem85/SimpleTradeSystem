package com.crypto.trading;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crypto.trading.converters.CryptoConverter;
import com.crypto.trading.entities.Crypto;
import com.crypto.trading.models.BinanceTicker;
import com.crypto.trading.models.HuobiTicker;
import com.crypto.trading.services.BinanceTickerService;
import com.crypto.trading.services.CryptoService;
import com.crypto.trading.services.HuobiTickerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CryptoScheduler {
	
	@Autowired
	private BinanceTickerService binanceTickerService;
	
	@Autowired
	private HuobiTickerService huobiTickerService;
	
	@Autowired
	private CryptoService cryptoService;
	
	@Scheduled(fixedDelay = 10000)
	public void updateCryptoBestPrice() throws Exception {
		log.info("RUNNING SCHEDULE");
		List<BinanceTicker> binanceTickers = this.binanceTickerService.getSupportedTickers();
		List<HuobiTicker> houbiTickers = this.huobiTickerService.getSupportedTickers();
		
		Map<String, Crypto> binanceCollectors = binanceTickers.stream()
				.map(item -> CryptoConverter.toCrypto(item, this.binanceTickerService.getSource()))
				.collect(Collectors.toMap(Crypto::getSymbol, Function.identity()));
		
		Map<String, Crypto> huobiCollectors = houbiTickers.stream()
				.map(item -> CryptoConverter.toCrypto(item, this.huobiTickerService.getSource()))
				.collect(Collectors.toMap(Crypto::getSymbol, Function.identity()));
		
		for (Map.Entry<String, Crypto> entry : huobiCollectors.entrySet()) {
			String symbol = entry.getKey().toUpperCase();
			if(binanceCollectors.containsKey(symbol)) {
				Crypto crypto = binanceCollectors.get(symbol);
				// Find best SELL price.
				if(entry.getValue().getBidPrice() > crypto.getBidPrice()) {
					crypto.setBidPrice(entry.getValue().getBidPrice());
					crypto.setBidSource(entry.getValue().getBidSource());
				}
				
				// Find best BUY price.
				if(entry.getValue().getAskPrice() < crypto.getAskPrice()) {
					crypto.setAskPrice(entry.getValue().getAskPrice());
					crypto.setAskSource(entry.getValue().getBidSource());
				}
				
			} else {
				binanceCollectors.put(symbol, entry.getValue());
			}
		}
		
		for (Map.Entry<String, Crypto> entry : binanceCollectors.entrySet()) {
			this.cryptoService.updateCrypto(entry.getValue());
		}
		
	}
}
