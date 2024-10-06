package com.kusoduck.account.dao;

import java.util.List;

import com.kusoduck.account.entity.Account;

public interface IAccountDAO {

	List<Account> findAll();
	
	Account findById(int theId);
	
	Account save(Account theAccount);
	
	void deleteById(int theId);
}