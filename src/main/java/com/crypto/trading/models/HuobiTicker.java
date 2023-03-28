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
public class HuobiTicker {
	
	private String symbol;
	
	private float open;
	
	private float high;
	
	private float low;
	
	private float close;
	
	private float amount;
	
	private float vol;
	
	private float count;
	
	private float bid;
	
	private float bidSize;
	
	private float ask;
	
	private float askSize;
}
