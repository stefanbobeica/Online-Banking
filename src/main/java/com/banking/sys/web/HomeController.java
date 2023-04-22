package com.banking.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/conturiBancare")
	public String conturiBancare() {
		return "conturiBancare";
	}

	@GetMapping("/carduri")
	public String carduri() {
		return "carduri";
	}

	@GetMapping("/vaults")
	public String vaults() {
		return "vaults";
	}

	@GetMapping("/tranzactii")
	public String tranzactii() {
		return "tranzactii";
	}

	@GetMapping("/myAccount")
	public String myAccount() {
		return "myAccount";
	}
}
