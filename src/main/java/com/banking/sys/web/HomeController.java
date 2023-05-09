package com.banking.sys.web;

import com.banking.sys.model.Account;
import com.banking.sys.service.AccountService;
import com.banking.sys.service.TransactionService;
import com.banking.sys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

	private AccountService accountService;
	private TransactionService transactionService;

	public HomeController(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}
	
	/////////////////////////////////////////////////////
	public static class Tranzactie
	{
		public String tip;
		public String catre;
		public double valoare;
		public String data;
		public String deLa;
		public boolean send;

		public Tranzactie(){
			this.tip = "INTER/EXTRA";
			this.catre = "IBAN/MERCHANT";
			this.valoare = 231.32;
			this.deLa = "IBAN/MERCHANT";
			this.send = true;
			this.data = "23:04:2021";
		}
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
		model.addAttribute("conturi", accountService.getAll());
		return "conturiBancare";
	}

	@GetMapping("/contBancar/{id}")
	public String contBancar(@PathVariable("id") Long id, Model model){

		Account account = accountService.getAccountById(id);
		model.addAttribute("cont", account);
        model.addAttribute("tranzactii", transactionService.findAllTransactionsByAccount(account));
		return "contBancar";
	}
	@PostMapping("/creareContBancar")
	public String createBankAccount(@ModelAttribute Account account, Model model) {
		Account savedAccount = accountService.saveAccount(account);
		model.addAttribute("account", savedAccount);
		return "somePageToShowTheAccount";
	}
	@PostMapping("/stergeContBancar")
	public String stergereContBancar(@RequestParam("iban") String iban, RedirectAttributes redirectAttributes) {
		accountService.deleteAccountByIban(iban);
		redirectAttributes.addFlashAttribute("message", "Contul a fost sters cu succes!");
		return "conturiBancare";
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

	@GetMapping("/tranzactii")
	public String tranzactii() {
		return "tranzactii";
	}

	@GetMapping("/myAccount")
	public String myAccount() {
		return "myAccount";
	}
}
