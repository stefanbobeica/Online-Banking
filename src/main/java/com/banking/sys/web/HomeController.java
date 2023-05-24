package com.banking.sys.web;

import com.banking.sys.model.Account;
import com.banking.sys.model.Card;
import com.banking.sys.model.User;
import com.banking.sys.model.Vault;
import com.banking.sys.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

	private AccountService accountService;
	private UserService userService;
	@Autowired
	private CardService cardService;
	@Autowired
	private VaultService vaultService;

	public HomeController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	/////////////////////////////////////////////////

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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		User user= userService.getByEmail(userEmail);
		List<Vault> depozite = vaultService.getAllVaultsByUser(user);
		 model.addAttribute("depozite",depozite);
		////////////////////////////////////////////////

		return "vaults";
	}






	@GetMapping("/carduri")
	public String carduri(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		User user= userService.getByEmail(userEmail);
		List<Account> conturi = accountService.getAccountByUser(user);
		ArrayList<Card> carduri = new ArrayList<Card>();
		for(Account c : conturi){
			carduri.addAll(cardService.getCardsByIBAN(c.getIBAN()));
		}
		Account cont = new Account();
		String selectedIban = new String();
		model.addAttribute("selectedIban", selectedIban);

		model.addAttribute("cont", cont);
		model.addAttribute("conturi",conturi);
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
