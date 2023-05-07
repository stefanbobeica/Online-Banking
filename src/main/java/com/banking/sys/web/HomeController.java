package com.banking.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {
	///////////////////////////////////////////////////
	public static class Cont
	{
		public String IBAN;
		public double sold;
		public int id; // !!!!!IMPORTANT

		public Cont(String str, double sold){
			this.IBAN = str;
			this.id = ((int)sold);
			this.sold = sold;
		}
	}
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
		///////////////////////////////////////////////
		List<Cont> produse = Arrays.asList(
                new Cont("RO49AAAA1B31007593840000", 10.0),
                new Cont("RO49AAAA1B31007593840000", 20.0),
                new Cont("RO49AAAA1B31007593840000", 30.0),
				new Cont("RO49AAAA1B31007593840000", 30.0),
				new Cont("RO49AAAA1B31007593840000", 30.0)
        );
        model.addAttribute("conturi", produse);
		////////////////////////////////////////////////
		return "conturiBancare";
	}

	@GetMapping("/contBancar")
	public String contBancar(Model model){
		////////////////////////////////////////////////
		Cont cont = new Cont("RO49AAAA1B31007593840000", 10.0);
		cont.id = 234;
		model.addAttribute("cont", cont);

		List<Tranzactie> tranzactii = Arrays.asList(
                new Tranzactie(),
				new Tranzactie(),
				new Tranzactie(),
				new Tranzactie(),
				new Tranzactie(),
				new Tranzactie()
        );
        model.addAttribute("tranzactii", tranzactii);
		///////////////////////////////////////////////
		return "contBancar";
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
