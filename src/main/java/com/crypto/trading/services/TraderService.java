package com.crypto.trading.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.repositories.TraderRepository;

@Service
public class TraderService {

	@Autowired
	private TraderRepository traderRepository;
}
