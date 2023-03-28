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
@Table(name = "Trader")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Trader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRADER_ID")
	@Column(name = "trader_id") 
	private Long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column
	private String email;

	@Column
	private String password;
}
