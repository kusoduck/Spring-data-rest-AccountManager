package com.kusoduck.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @Controller is used to create traditional Spring MVC controllers. It primarily handles HTTP requests and returns a view.
 */
@Controller
@RequestMapping("/api/thymeleaf")
public class DemoController {

	@GetMapping("/hello")
	public String sayHello(Model theMadal) {
		theMadal.addAttribute("theDate", new java.util.Date());
		return "helloworld";
	}
	
	@GetMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld2";
	}
	
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/processForm2")
	public String processForm2(HttpServletRequest request, Model model) {
		// read the request parameter from the HTML form
		String userName = request.getParameter("userName");
		
		// convert the data to all caps
		userName = userName.toUpperCase();
		
		// create the message
		String result = "Yo! "+ userName;
		
		// add message to the model
		model.addAttribute("message", result);
		
		return "helloworld2";
	}
	
	@PostMapping("/processForm3")
	public String processForm3(@RequestParam("userName") String theName, Model model) {
		
		// convert the data to all caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "From v3! "+ theName;
		
		// add message to the model
		model.addAttribute("message", result);
		
		return "helloworld2";
	}
}
