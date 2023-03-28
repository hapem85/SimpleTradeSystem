package com.crypto.trading.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crypto.trading.models.BinanceTicker;

import lombok.Getter;

@Service
public class BinanceTickerService {
	
	@Getter
	@Value("${binance.source}")
    private String source;
	
	@Value("${symbols.supported}")
	private String[] supportedSymbols;
	
	public List<BinanceTicker> getSupportedTickers() {
		RestTemplate restTemplate = new RestTemplate();
		BinanceTicker[] results = restTemplate.getForObject(this.source, BinanceTicker[].class);
		List<BinanceTicker> tickers = Arrays.asList(results);
		return CollectionUtils.emptyIfNull(tickers).stream()
				.filter(ticker -> StringUtils.equalsAny(ticker.getSymbol(), supportedSymbols))
				.collect(Collectors.toList());
	}
}
