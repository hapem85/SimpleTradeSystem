package com.crypto.trading.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.trading.converters.TradeTransactionConverter;
import com.crypto.trading.entities.Crypto;
import com.crypto.trading.entities.TradeTransaction;
import com.crypto.trading.entities.WalletBalance;
import com.crypto.trading.models.TradeModel;
import com.crypto.trading.models.TradeTransactionModel;
import com.crypto.trading.repositories.CryptoRepository;
import com.crypto.trading.repositories.TradeTransactionRepository;
import com.crypto.trading.repositories.WalletBalanceRepository;
import com.crypto.trading.utils.StreamUtils;

@Service
public class TradeTransactionService {

	@Autowired
	private TradeTransactionRepository tradeTransactionRepository;
	
	@Autowired
	private CryptoRepository cryptoRepository;
	
	@Autowired
	private WalletBalanceRepository walletBalanceRepository;

	public List<TradeTransactionModel> getTraderTransactions(Long traderId) {
		List<TradeTransaction> entities = StreamUtils.toList(tradeTransactionRepository.findByTraderId(traderId));
		return TradeTransactionConverter.fromEntities(entities);
	}

	@Transactional
	public TradeTransactionModel buyCrypto(TradeModel trade) throws Exception {
		
		Crypto crypto = this.cryptoRepository.findById(trade.getCryptoId())
				.orElseThrow(() -> new Exception("Not found crypto!"));
		
		// get debited amount.
		float debitedCryptoAmount = crypto.getAskPrice() * trade.getTradeAmount();
		
		// Find the pay wallet.
		WalletBalance debitedWallet = this.walletBalanceRepository.findFirstByTraderIdAndCryptoSymbol(trade.getTraderId(), trade.getDebitedCryptoName())
				.orElseThrow(() -> new Exception("No wallet for crypto name " + trade.getDebitedCryptoName()));
		
		if(debitedWallet.getCryptoAmount() < debitedCryptoAmount) {
			throw new Exception("No sufficient amount to buy.");
		}
		
		// Update the remaining wallet.
		debitedWallet.setCryptoAmount(debitedWallet.getCryptoAmount() - debitedCryptoAmount);
		this.walletBalanceRepository.save(debitedWallet);
		
		
		// Find the credited wallet.
		WalletBalance creditedWallet = this.walletBalanceRepository.findFirstByTraderIdAndCryptoSymbol(trade.getTraderId(), trade.getCreditedCryptoName())
				.orElse(WalletBalance.builder()
						.cryptoSymbol(trade.getCreditedCryptoName())
						.traderId(trade.getTraderId())
						.cryptoAmount(0)
						.build());
		creditedWallet.setCryptoAmount(creditedWallet.getCryptoAmount() + trade.getTradeAmount());
		this.walletBalanceRepository.save(creditedWallet);
		
		TradeTransaction transaction = TradeTransaction.builder()
				.creditedCryptoName(trade.getCreditedCryptoName())
				.creditedCryptoAmount(trade.getTradeAmount())
				.debitedCryptoName(trade.getDebitedCryptoName())
				.debitedCryptoAmount(debitedCryptoAmount)
				.fromSource(crypto.getAskSource())
				.traderId(trade.getTraderId())
				.tradingDate(new Date())
				.build();
		TradeTransaction transactionEntity = this.tradeTransactionRepository.save(transaction);
		return TradeTransactionConverter.fromEntity(transactionEntity);
	}
	
	@Transactional
	public TradeTransactionModel sellCrypto(TradeModel trade) throws Exception {
		
		Crypto crypto = this.cryptoRepository.findById(trade.getCryptoId())
				.orElseThrow(() -> new Exception("Not found crypto!"));
		
		// Find the pay wallet.
		WalletBalance debitedWallet = this.walletBalanceRepository.findFirstByTraderIdAndCryptoSymbol(trade.getTraderId(), trade.getDebitedCryptoName())
				.orElseThrow(() -> new Exception("No wallet for crypto name " + trade.getDebitedCryptoName()));
		
		if(debitedWallet.getCryptoAmount() < trade.getTradeAmount()) {
			throw new Exception("No sufficient amount to sell.");
		}
		
		// Update the remaining debited wallet.
		debitedWallet.setCryptoAmount(debitedWallet.getCryptoAmount() - trade.getTradeAmount()); // amount of coins
		this.walletBalanceRepository.save(debitedWallet);
		
		// get debited amount.
		float creditedCryptoAmount = crypto.getBidPrice() * trade.getTradeAmount(); // amount of money
		
		// Find the credited wallet.
		WalletBalance creditedWallet = this.walletBalanceRepository.findFirstByTraderIdAndCryptoSymbol(trade.getTraderId(), trade.getCreditedCryptoName())
				.orElse(WalletBalance.builder()
						.cryptoSymbol(trade.getCreditedCryptoName())
						.traderId(trade.getTraderId())
						.cryptoAmount(0)
						.build());
		creditedWallet.setCryptoAmount(creditedWallet.getCryptoAmount() + creditedCryptoAmount);
		
		this.walletBalanceRepository.save(creditedWallet);
		
		TradeTransaction transaction = TradeTransaction.builder()
				.creditedCryptoName(trade.getCreditedCryptoName())
				.creditedCryptoAmount(creditedCryptoAmount)
				.debitedCryptoName(trade.getDebitedCryptoName())
				.debitedCryptoAmount(trade.getTradeAmount())
				.fromSource(crypto.getAskSource())
				.traderId(trade.getTraderId())
				.tradingDate(new Date())
				.build();
		TradeTransaction transactionEntity = this.tradeTransactionRepository.save(transaction);
		return TradeTransactionConverter.fromEntity(transactionEntity);
	}
}
