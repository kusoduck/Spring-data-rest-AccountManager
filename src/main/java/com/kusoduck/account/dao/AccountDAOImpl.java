package com.kusoduck.account.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kusoduck.account.entity.Account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AccountDAOImpl implements IAccountDAO {

	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public AccountDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public List<Account> findAll(){
		TypedQuery<Account> typedQuery = entityManager.createQuery("From Account order by accountName", Account.class);
		return typedQuery.getResultList();
	}

	@Override
	public Account findById(int theId) {
		// get account
		Account dbAccount = entityManager.find(Account.class, theId);
		
		return dbAccount;
	}

	@Override
	public Account save(Account theAccount) {
		// save account
		// merge: if id is 0 then insert else update
		Account dbAccount = entityManager.merge(theAccount);
		return dbAccount;
	}

	@Override
	public void deleteById(int theId) {
		// find account id
		Account dbAccount = entityManager.find(Account.class, theId);
		
		// remove account
		entityManager.remove(dbAccount);
	}
}
