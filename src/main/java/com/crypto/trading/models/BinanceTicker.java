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
public class BinanceTicker {
	
	private String symbol;
	
	private String bidPrice;
	
	private String bidQty;

	private String askPrice;
	
	private String askQty;
}
