package com.crypto.trading.models;

import java.util.Date;

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
public class TradeTransactionModel {
	private Long id;
	
	private Long traderId;
	
	private String creditedCryptoName;
	
	private float creditedCryptoAmount;
	
	private String debitedCryptoName;
	
	private float debitedCryptoAmount;
	
	private String fromSource;
	
	private Date tradingDate;
}
