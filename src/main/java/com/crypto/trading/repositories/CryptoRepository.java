package com.crypto.trading.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crypto.trading.entities.Crypto;

@Repository
public interface CryptoRepository extends CrudRepository<Crypto, Long>{
	Optional<Crypto> findBySymbol(String symbol);
}
