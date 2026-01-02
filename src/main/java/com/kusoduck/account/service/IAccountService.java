package com.kusoduck.account.service;

import java.util.List;

import com.kusoduck.account.entity.Account;

public interface IAccountService {
	void init();

	List<Account> findAll();

	List<Account> findByUserId(int theUserId);

	Account findById(int theId);

	Account save(Account theAccount);

	void deleteById(int theId);
}
