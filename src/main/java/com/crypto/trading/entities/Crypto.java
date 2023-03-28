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
@Table(name = "Crypto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Crypto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CRYPTO_ID")
	@Column(name = "crypto_id")
	private Long id;
	
	@Column
	private String symbol;
	
	@Column(name = "bid_price")
	private float bidPrice;
	
	@Column(name = "bid_source")
	private String bidSource;

	@Column(name = "ask_price")
	private float askPrice;
	
	@Column(name = "ask_source")
	private String askSource;
}
