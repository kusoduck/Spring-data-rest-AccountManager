package com.kusoduck.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.kusoduck.account.dao.AccountDAO;
import com.kusoduck.account.entity.Account;

@SpringBootApplication
@ComponentScan(basePackages = "com.kusoduck")
public class AccountManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
		return runner -> {
//						createAccount(accountDAO);
//			findAll(accountDAO);
			//			 findByAccountName(accountDAO,"Fake_A");
			// updateAccount(accountDAO);
			// deleteAccount(accountDAO);
			//			deleteAllAccounts(accountDAO);
		};
	}

	private void deleteAllAccounts(AccountDAO accountDAO) {
		// TODO Auto-generated method stub
		System.out.println("Deleting all accounts");
		int numRowsDeleted = accountDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteAccount(AccountDAO accountDAO) {
		int accountId = 8;
		System.out.println("Deleting account id: " + accountId);
		accountDAO.delete(accountId);
	}

	private void updateAccount(AccountDAO accountDAO) {
		int accountId = 8;
		Account account = accountDAO.findById(accountId);
		System.out.println(account.toString());
		account.setPassword("456");
		accountDAO.update(account);
		account = accountDAO.findById(accountId);
		System.out.println(account.toString());
	}

	private void findByAccountName(AccountDAO accountDAO, String accountName) {
		for (Account account : accountDAO.findByAccountName(accountName)) {
			System.out.println(account.toString());
		}
	}

	private void findAll(AccountDAO accountDAO) {
		for (Account account : accountDAO.findAll()) {
			System.out.println(account.toString());
		}
	}

	private void createAccount(AccountDAO accountDAO) {
		Account accountTemp = new Account("Fake_B", "cher", "0720");
		accountDAO.save(accountTemp);
		System.out.println("Generated id: " + accountTemp.getId());
	}
}
