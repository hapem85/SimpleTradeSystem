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
public class TradeModel {
	
	private Long traderId;
	
	private Long cryptoId;
	
	/*
	 * Buy: ETH or BTC
	 * Sell: USDT
	 */
	private String creditedCryptoName;
	
	/*
	 * Buy: USDT
	 * Sell: ETH or BTC
	 */
	private String debitedCryptoName;
	
	// Quantity of coins
	private float tradeAmount;
}
