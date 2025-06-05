package com.kusoduck.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.kusoduck.account.model.Student;

import jakarta.validation.Valid;

@Controller
public class StudentController {

	@Value("${countries}")
	private List<String> countries;
	@Value("${languages}")
	private List<String> languages;
	@Value("${systems}")
	private List<String> systems;

	@InitBinder // pre-process all web requests coming into controller
	public void initBinder(WebDataBinder dataBinder) {
		// remove leading and trailing whitespace (true: if only white space .. trim it to null
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showStudentForm")
	public String showForm(Model theModel) {
		// Add student object to the model
		theModel.addAttribute("student", new Student());
		theModel.addAttribute("countries", countries);
		theModel.addAttribute("languages", languages);
		theModel.addAttribute("systems", systems);
		return "student-form";
	}

	@PostMapping("/processStudentForm")
	// @valid: perform validation
	// @ModelAttribute: read the form data that model attribute student
	// BindingResult is result of validation
	public String processStudentForm(@Valid BindingResult theBindingResult, Model theModel) {

		System.out.println("Binding results: "+theBindingResult.toString());

		if(theBindingResult.hasErrors()) {
			/* need theModel set attribute to create options for proerties  */
			theModel.addAttribute("countries", countries);
			theModel.addAttribute("languages", languages);
			theModel.addAttribute("systems", systems);
			return "student-form";
		}
		return "student-confirmation";
	}
}
