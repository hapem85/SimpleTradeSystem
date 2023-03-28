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
public class TraderModel {
	
	private Long id;
	
	private String fullName;
	
	private String userName;
	
	private String email;

	private String password;
}
