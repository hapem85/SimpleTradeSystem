package com.crypto.trading.converters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crypto.trading.entities.Trader;
import com.crypto.trading.models.TraderModel;
import com.crypto.trading.utils.StreamUtils;

public class TraderConverter {
	
	public final static TraderModel fromEntity(Trader entity) {
		return Optional.ofNullable(entity)
				.map(item -> TraderModel.builder()
						.email(item.getEmail())
						.fullName(item.getFullName())
						.id(item.getId())
						.userName(item.getUserName())
						.build())
				.orElse(null);
	}

	public static List<TraderModel> fromEntities(List<Trader> entities) {
		return StreamUtils.safeStream(entities)
				.map(entity -> fromEntity(entity))
				.collect(Collectors.toList());
	}
}
