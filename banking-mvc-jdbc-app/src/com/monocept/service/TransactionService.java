package com.monocept.service;

import java.io.IOException;
import java.util.List;

import com.monocept.model.Transaction;
import com.monocept.repository.AccountRepository;
import com.monocept.repository.TransactionRepository;

public class TransactionService {
	private static TransactionService instance;
	private static Object mutex = new Object();
	private TransactionRepository transactionRepository;

	public TransactionService() {
		System.out.println("Transaction service started");
		transactionRepository = new TransactionRepository();
	}

	public static TransactionService getInstance() {
		TransactionService result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new TransactionService();
			}
		}
		return instance;
	}

	public List<Transaction> getTransactions(String name) {
		return transactionRepository.getTransactions(name);
	}

	public void doTransaction(Transaction transaction, double balanceBeforeTransaction) {
		transactionRepository.doTransaction(transaction, balanceBeforeTransaction);
	}

	public void savePassbook(String name) {
		try {
			transactionRepository.savePassbook(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
