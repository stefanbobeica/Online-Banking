package com.banking.sys.web;

import com.banking.sys.dto.UserRegistrationDto;
import com.banking.sys.service.TwoFaServiceImpl;
import com.banking.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private  TwoFaServiceImpl twoFaService;
	private UserService userService;

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		twoFaService.sendSimpleEmail("stefanbobeica25@gmail.com", "Hello", "1234");
		userService.save(registrationDto);

		return "redirect:/registration?success";
	}
}
