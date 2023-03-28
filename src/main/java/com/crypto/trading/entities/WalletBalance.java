package com.crypto.trading.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wallet_balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WalletBalance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERWALLET_ID")
	@Column(name = "wallet_balance_id")
	private Long id;
	
	@Column(name = "trader_id")
	private Long traderId;
	
	@Column(name = "crypto_symbol")
	private String cryptoSymbol;
	
	@Column(name = "crypto_amount")
	private float cryptoAmount;

}
