package com.crypto.trading.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CryptoModel {
	
	private Long id;
	
	private String symbol;
	
	private float bidPrice;
	
	private String bidSource;

	private float askPrice;
	
	private String askSource;
}
