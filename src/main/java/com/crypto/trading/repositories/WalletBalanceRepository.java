package com.crypto.trading.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crypto.trading.entities.WalletBalance;

@Repository
public interface WalletBalanceRepository extends CrudRepository<WalletBalance, Long>{
	List<WalletBalance> findByTraderId(Long traderId);

	Optional<WalletBalance> findFirstByTraderIdAndCryptoSymbol(Long traderId, String debitedCryptoName);
}
