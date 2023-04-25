package com.banking.sys.web;

import com.banking.sys.dto.UserRegistrationDto;
// import com.banking.sys.service.TwoFaServiceImpl;
import com.banking.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	// private  TwoFaServiceImpl twoFaService;
	private UserService userService;

	public RegisterController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		// twoFaService.sendSimpleEmail("stefanbobeica25@gmail.com", "Hello", "1234");
		userService.save(registrationDto);

		return "redirect:/register?success";
	}
}
