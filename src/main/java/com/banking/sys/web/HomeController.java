package com.banking.sys.web;

import com.banking.sys.model.Account;
import com.banking.sys.model.User;
import com.banking.sys.service.AccountService;
import com.banking.sys.service.TransactionService;
import com.banking.sys.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

	private AccountService accountService;
	private UserService userService;

	public HomeController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	public static class Vault
	{
		public int id; // !!!!!IMPORTANT
		public String nume;
		public double valoare;

		public Vault(){
			this.nume = "Vaultulescu";
			this.valoare = 200.13;
			this.id = 23;
		}
	}
	/////////////////////////////////////////////////
	public static class Card
	{
		public String numar;
		public String swift;
		public String dataExpirare;
		public String tip;

		public Card(){
			this.numar = "1344123412341234";
			this.swift = "RORZBR";
			this.dataExpirare = "23:23:2002";
			this.tip = "VISA";
		}
	}
	//////////////////////////////////////////////////
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/conturiBancare")
	public String conturiBancare(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		User user= userService.getByEmail(userEmail);
		model.addAttribute("conturi",accountService.getAccountByUser(user));
		return "conturiBancare";
	}

	//TODO : implement this
	@GetMapping("/deschidereCont")
	public String createBankAccount(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		User user= userService.getByEmail(userEmail);

		String generatedIban = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
		while (accountService.getAccountByIBAN(generatedIban) != null) {
			generatedIban = RandomStringUtils.randomAlphanumeric(13).toUpperCase();
		}

		Account newAccount = new Account(generatedIban, 0.0);

		newAccount.setUser(user);

		accountService.saveAccount(newAccount);

		return "redirect:/conturiBancare/";
	}

	@GetMapping("/vaults")
	public String vaults(Model model) {
		///////////////////////////////////////////////
		List<Vault> vaults = Arrays.asList(
                new Vault(),
				new Vault(),
				new Vault(),
				new Vault()
        );
        model.addAttribute("vaults", vaults);
		////////////////////////////////////////////////	

		return "vaults";
	}

	@GetMapping("/vault")
	public String vault(Model model) {
		Vault vault = new Vault();
		model.addAttribute("vault", vault);

		return "vault";
	}

	@GetMapping("/carduri")
	public String carduri(Model model) {
		///////////////////////////////////////////////
		List<Card> carduri = Arrays.asList(
                new Card(),
				new Card(),
				new Card(),
				new Card()
        );
        model.addAttribute("carduri", carduri);
		////////////////////////////////////////////////	

		return "carduri";
	}
//
//	@GetMapping("/tranzactii")
//	public String tranzactii() {
//		return "tranzactii";
//	}

	@GetMapping("/myAccount")
	public String myAccount() {
		return "myAccount";
	}
}
