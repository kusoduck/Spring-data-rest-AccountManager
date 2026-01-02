package com.kusoduck.account.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kusoduck.account.entity.Account;
import com.kusoduck.account.service.AccountRepositoryService;
import com.kusoduck.account.service.IAccountService;
import com.kusoduck.user.entity.MyUserDetails;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	private IAccountService accountService;

//	@Autowired
//	public AccountController(@Qualifier("accountRepositoryService") IAccountService theAccountService) {
//		accountService = theAccountService;
//	}

	// also can do like this
	// 當一個bean只有一個建構子時，Spring會自動將該建構子標記為@Autowired。
	public AccountController(AccountRepositoryService theAccountService) {
		accountService = theAccountService;
	}

	@GetMapping("/list")
	public String getAccounts(Model theModel) {
//	public String getAccounts(@RequestParam("userId") int theUserId,Model theModel) {
//		List<Account> accounts = accountService.findAll();

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
	    int userId = userDetails.getUserId();
		List<Account> accounts = accountService.findByUserId(userId);
		theModel.addAttribute("accounts", accounts);
		// 檔案位置預設從templates開始
		return "account/list-accounts";
	}

	@GetMapping("/showFormForAdd")
	public String showFromForAdd(Model theModel) {
		Account account = new Account();
		theModel.addAttribute("account", account);
		return "account/account-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFromForUpdate(@RequestParam("id") int theId, Model theModel) {
		Account account = accountService.findById(theId);
		theModel.addAttribute("account", account);
		return "account/account-form";
	}

	@PostMapping("/save")
	// @ModelAttribute: read the form data that model attribute account
	public String saveAccount(@ModelAttribute("account") Account theAccount) {
		accountService.save(theAccount);
		//POST/Redirect/GET 模式可以幫助防止用戶進行重複提交表單數據
		return "redirect:/accounts/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int theId) {
		accountService.deleteById(theId);
		return "redirect:/accounts/list";
	}
}
