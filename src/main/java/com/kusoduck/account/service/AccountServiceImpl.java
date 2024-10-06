package com.kusoduck.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kusoduck.account.dao.AccountDAOImpl;
import com.kusoduck.account.entity.Account;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements IAccountService {

	private AccountDAOImpl accountDAOImpl;

	@Autowired
	public AccountServiceImpl(AccountDAOImpl theAccountDAOImpl) {
		accountDAOImpl = theAccountDAOImpl;
	}

	@Override
	public void init() {
		System.out.println("execute AccountServiceImpl init");
	}

	@Override
	public List<Account> findAll() {
		System.out.println("using accountDAOImpl ...");
		return accountDAOImpl.findAll();
	}

	@Override
	public Account findById(int theId) {
		return accountDAOImpl.findById(theId);
	}

	@Override
	@Transactional
	public Account save(Account theAccount) {
		return accountDAOImpl.save(theAccount);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		accountDAOImpl.deleteById(theId);
	}

}
