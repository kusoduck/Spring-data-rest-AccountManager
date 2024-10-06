package com.kusoduck.account.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusoduck.account.entity.Account;

//@RepositoryRestResource(path="banks") // << it's used for alias the path, use banks not accounts
public interface IAccountRepository extends JpaRepository<Account, Integer>{
	// it's all no need any code for basic CURD
	
	// add a method to sort by account name
	/*
	 * Spring Data JPA will parse the method name
	 * find all by
	 * order by account name ascending
	 */
	public List<Account> findAllByOrderByAccountNameAsc();
}
