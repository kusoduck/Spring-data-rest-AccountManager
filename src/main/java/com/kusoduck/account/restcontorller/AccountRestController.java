package com.kusoduck.account.restcontorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusoduck.account.dao.AccountDAO;
import com.kusoduck.account.dao.IAccountDAO;
import com.kusoduck.account.entity.Account;
import com.kusoduck.error.AccountNotFoundException;

import jakarta.annotation.PostConstruct;

/**
 * @RestController is a combination of @Controller and @ResponseBody annotations. 
 * The @ResponseBody annotation indicates that the return value of the method is directly sent to the client as the HTTP response body. 
 * It is commonly used to return JSON or XML data instead of a view.
 */
@RestController
@RequestMapping("/api")
public class AccountRestController {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private IAccountDAO iAccountDAO;

	@PostConstruct
	public void init() {
		// 这个方法会在对象初始化后被自动调用
		// 可以执行一些初始化工作
		System.out.println("Initializing ...");
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
//		return this.accountDAO.findAll();
		return iAccountDAO.findAll();
	}

	@GetMapping("/accounts/{accountId}")
	public Account getAccounts(@PathVariable int accountId) {

		if (accountId < 1) {
			throw new AccountNotFoundException("Account is not found - " + accountId);
		}

		return accountDAO.findById(accountId);
	}

}
