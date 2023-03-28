package com.crypto.trading.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crypto.trading.entities.WalletBalance;

@Repository
public interface WalletBalanceRepository extends CrudRepository<WalletBalance, Long>{

}
