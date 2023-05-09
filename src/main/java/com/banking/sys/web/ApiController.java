package com.banking.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.sys.model.Account;
import com.banking.sys.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private AccountService accountService;

    public ApiController(AccountService accountService){
        this.accountService = accountService;
    }

    ///////////////////////////////////////////////////
    public static class Cont
    {
        public String IBAN;

        public Cont(){
            this.IBAN = "HU93116000060000000012345676";
        } 
    }
    public static class Tranzactie
    {
        public String nume;
        public double suma;
        public String numeComerciant;

        public Tranzactie(){
            this.suma = 234.23;
            this.nume = "Tranzactie";
            this.numeComerciant = "Afgan SRL";
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    ///////////////////////////////////////////////////

    public static int ok = 0;

    @PostMapping("/api/tranzactii")
    public List<Tranzactie> getTranzactii() {
        //logger.info("Request POST primit la /api/tranzactii");
        //ok++;
        List<Tranzactie> tranzactii = new ArrayList<>();
        
        //for(int i = 0; i<= ok; i++)
        tranzactii.add(new Tranzactie());
        tranzactii.add(new Tranzactie());

        return tranzactii;
    }

    @PostMapping("/api/conturi-bancare")
    public List<Cont> getConturiBancare() {
        //logger.info("Request POST primit la /api/tranzactii");

        List<Cont> conturi = new ArrayList<>();
        conturi.add(new Cont());
        conturi.add(new Cont());

        return conturi;
    }

    // @PostMapping("/api/conturi-bancare")
    // public List<Account> getConturiBancare() {
    //     logger.info("Request POST primit la /api/tranzactii");
    //     return accountService.getAll();
    // }
}