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
public class WalletBalanceModel {
	
	private Long id;
	
	private Long traderId;
	
	private String cryptoSymbol;
	
	private float cryptoAmount;

}
