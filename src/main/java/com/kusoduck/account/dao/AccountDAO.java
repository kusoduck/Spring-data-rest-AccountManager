package com.kusoduck.account.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kusoduck.account.entity.Account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Component
public class AccountDAO {

	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public AccountDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Account findById(Integer id) {
		return this.entityManager.find(Account.class, id);
	}

	public List<Account> findAll(){
		TypedQuery<Account> typedQuery = this.entityManager.createQuery("From Account order by accountName", Account.class);
		return typedQuery.getResultList();
	}

	public List<Account> findByAccountName(String theAccountName){
		TypedQuery<Account> typedQuery = this.entityManager.createQuery("From Account where account_name=:theData order by accountName", Account.class);
		typedQuery.setParameter("theData", theAccountName);
		return typedQuery.getResultList();
	}

	@Transactional
	public void save(Account account) {
		this.entityManager.persist(account);
	}

	@Transactional
	public void update(Account account) {
		this.entityManager.merge(account);
	}

	@Transactional
	public void delete(Integer id) {
		Account account = findById(id);
		this.entityManager.remove(account);
	}

	@Transactional
	public int deleteAll() {
		return this.entityManager.createQuery("delete from Account").executeUpdate();
	}
}
