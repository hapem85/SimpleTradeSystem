package com.crypto.trading.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crypto.trading.entities.TradeTransaction;

@Repository
public interface TradeTransactionRepository extends CrudRepository<TradeTransaction, Long>{

	List<TradeTransaction> findByTraderId(Long traderId);

}
