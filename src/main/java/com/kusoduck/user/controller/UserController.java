package com.kusoduck.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kusoduck.user.model.UserRegistration;
import com.kusoduck.user.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegistration());
        return "user/register-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistration form) {
        userService.registerUser(form.getUsername(), form.getPassword());
        return "redirect:/showLoginPage";
    }
}
