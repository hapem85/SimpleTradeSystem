package com.crypto.trading.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.converters.TraderConverter;
import com.crypto.trading.models.TraderModel;
import com.crypto.trading.repositories.TraderRepository;
import com.crypto.trading.utils.StreamUtils;

@Service
public class TraderService {

	@Autowired
	private TraderRepository traderRepository;

	public TraderModel getTraderById(Long traderId) throws Exception {
		return this.traderRepository.findById(traderId)
				.map(trader -> TraderConverter.fromEntity(trader))
				.orElseThrow(() -> new Exception("Not found"));
	}

	public List<TraderModel> getTraders() {
		return TraderConverter.fromEntities(StreamUtils.toList(this.traderRepository.findAll()));
	}
}
