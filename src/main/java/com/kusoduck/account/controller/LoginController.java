package com.kusoduck.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
//		return "plain-login";
		return "user/fancy-login";
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
}
