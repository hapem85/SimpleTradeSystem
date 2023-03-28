package com.crypto.trading.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Trade_Transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TradeTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRADETRANSACTION_ID")
	@Column(name = "trade_transaction_id") 
	private Long id;
	
	@Column(name = "trader_id")
	private Long traderId;
	
	@Column(name = "credited_crypto_name")
	private String creditedCryptoName;
	
	@Column(name = "credited_crypto_amount")
	private float creditedCryptoAmount;
	
	@Column(name = "debited_crypto_name")
	private String debitedCryptoName;
	
	@Column(name = "debited_crypto_amount")
	private float debitedCryptoAmount;
	
	@Column(name = "from_source")
	private String fromSource;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "trading_date")
	private Date tradingDate;
}
