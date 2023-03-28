package com.crypto.trading.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crypto.trading.models.HuobiData;
import com.crypto.trading.models.HuobiTicker;

import lombok.Getter;

@Service
public class HuobiTickerService {
	
	@Getter
	@Value("${huobi.source}")
    private String source;
	
	@Value("${symbols.supported}")
	private String[] supportedSymbols;
	
	public List<HuobiTicker> getSupportedTickers() {
		RestTemplate restTemplate = new RestTemplate();
		HuobiData data = restTemplate.getForObject(this.source, HuobiData.class);
		return CollectionUtils.emptyIfNull(data.getData()).stream()
				.filter(ticker -> StringUtils.equalsAny(ticker.getSymbol().toUpperCase(), supportedSymbols))
				.collect(Collectors.toList());
	}
}
