package com.kusoduck.account.restcontorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kusoduck.account.entity.Account;
import com.kusoduck.account.service.IAccountService;

@RestController
@RequestMapping("/api/new")
public class NewAccountRestController {

	// private AccountDAOImpl accountDAOImpl;
	// >> refactor code to use AccountService
	private IAccountService accountService;

	// quick and dirty: inject account dao(use constructor injection)
//	public NewAccountController(AccountDAOImpl theAccountDAOImpl) {
//		accountDAOImpl = theAccountDAOImpl;
//	}

	@Autowired
	public NewAccountRestController(@Qualifier("accountServiceImpl") IAccountService theAccountService) {
		accountService = theAccountService;
		accountService.init();
	}

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return accountService.findAll();
	}

	@GetMapping("/accounts/{accountId}") // by default, the path variable must be same
	public Account getAccount(@PathVariable int accountId) { // by default, the path variable must be same
		Account account = accountService.findById(accountId);
		if (account == null) {
			throw new RuntimeException("Account id not found - " + accountId);
		}
		return account;
	}

	@PostMapping("/accounts")
	public Account addAccount(@RequestBody Account theAccount) {
		theAccount.setId(0);
		return accountService.save(theAccount);
	}

	@PutMapping("/accounts")
	public Account updateAccount(@RequestBody Account theAccount) {
		return accountService.save(theAccount);
	}

	@DeleteMapping("/accounts/{accountId}")
	public String deleteAccount(@PathVariable int accountId) {
		Account account = accountService.findById(accountId);
		if (account == null) {
			throw new RuntimeException("Account id not found - " + accountId);
		}
		accountService.deleteById(accountId);
		return "Deleted account id - " + accountId;
	}
}
