package com.kusoduck.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kusoduck.account.dao.IAccountRepository;
import com.kusoduck.account.entity.Account;
import com.kusoduck.util.EncryptUtils;

@Service
public class AccountRepositoryService implements IAccountService {

	private IAccountRepository accountRepository;

	@Autowired
	public AccountRepositoryService(IAccountRepository theAccountRepository) {
		accountRepository = theAccountRepository;
	}

	@Override
	public void init() {
		System.out.println("execute AccountRepositoryService init");
	}

	@Override
	public List<Account> findAll() {
		System.out.println("using accountRepository ...");
//		return accountRepository.findAll(); >> basic CURD

		List<Account> accounts = accountRepository.findAllByOrderByAccountNameAsc();
		for(Account account : accounts) {
			decrytPassword(account);
		}
		return accounts;
	}

	@Override
	public Account findById(int theId) {
		Optional<Account> result = accountRepository.findById(theId);
		if (result.isPresent()) {
			Account account = result.get();
			decrytPassword(account);
			return account;
		}
		return null;
	}

	@Override
	public Account save(Account theAccount) {
		try {
			theAccount.setPassword(EncryptUtils.encryptPassword(theAccount.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountRepository.save(theAccount);
	}

	@Override
	public void deleteById(int theId) {
		accountRepository.deleteById(theId);
	}

	private void decrytPassword(Account account) {
		try {
			account.setPassword(EncryptUtils.decryptPassword(account.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
