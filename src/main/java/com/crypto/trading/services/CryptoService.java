package com.crypto.trading.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.converters.CryptoConverter;
import com.crypto.trading.entities.Crypto;
import com.crypto.trading.models.CryptoModel;
import com.crypto.trading.repositories.CryptoRepository;

@Service
public class CryptoService {

	@Autowired
	private CryptoRepository cryptoRepository;

	public Optional<Crypto> findBySymbol(String symbol) {
		return this.cryptoRepository.findBySymbol(symbol);
	}
	
	public Crypto updateCrypto(Crypto cryptoInput) {
		
		Optional<Crypto> dbCrypto = this.findBySymbol(cryptoInput.getSymbol());
		
		Crypto crypto = cryptoInput;
		if(dbCrypto.isPresent()) {
			crypto = dbCrypto.get();
			
			crypto.setBidSource(cryptoInput.getBidSource());
			crypto.setAskSource(cryptoInput.getAskSource());
			crypto.setAskPrice(cryptoInput.getAskPrice());
			crypto.setBidPrice(cryptoInput.getBidPrice());
		}
		return this.cryptoRepository.save(crypto);
	}
	
	public List<CryptoModel> getCryptos() {
		List<Crypto> entities = StreamSupport.stream(this.cryptoRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return CryptoConverter.fromEntities(entities);
	}
}
