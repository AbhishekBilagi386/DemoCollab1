package com.monocept.service;

import com.monocept.dto.AccountDTO;
import com.monocept.model.Account;
import com.monocept.repository.AccountRepository;

public class AccountService {
	private static AccountService instance;
	private static Object mutex = new Object();
	private AccountRepository accountRepository;

	public AccountService() {
		System.out.println("Account Service Started");
		accountRepository = new AccountRepository();
	}

	public static AccountService getInstance() {
		AccountService result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
					instance = result = new AccountService();
			}
		}
		return instance;
	}

	public AccountDTO getAccount(String name, String password) {
		Account account = null;
		AccountDTO accountDto = null;
		account = accountRepository.getAccount(name, password);
		accountDto = new AccountDTO(account.getName(), account.getBalance());
		return accountDto;
	}

	public void registerAccount(Account account) {
		accountRepository.registerAccount(account);
	}
}
