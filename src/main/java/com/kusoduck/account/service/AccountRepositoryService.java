package com.kusoduck.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kusoduck.account.dao.IAccountRepository;
import com.kusoduck.account.entity.Account;

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
		return accountRepository.findAllByOrderByAccountNameAsc();
	}

	@Override
	public Account findById(int theId) {
		Optional<Account> result = accountRepository.findById(theId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public Account save(Account theAccount) {
		return accountRepository.save(theAccount);
	}

	@Override
	public void deleteById(int theId) {
		accountRepository.deleteById(theId);
	}

}
