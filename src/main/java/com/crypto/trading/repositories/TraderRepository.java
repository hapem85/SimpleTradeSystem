package com.crypto.trading.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crypto.trading.entities.Trader;

@Repository
public interface TraderRepository extends CrudRepository<Trader, Long>{

}
