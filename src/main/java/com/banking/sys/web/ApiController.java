package com.banking.sys.web;

import com.banking.sys.model.Transaction;
import com.banking.sys.model.User;
import com.banking.sys.service.TransactionService;
import com.banking.sys.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.banking.sys.model.Account;
import com.banking.sys.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/tranzactii")
public class ApiController {
    private AccountService accountService;
    private UserService userService;
    private TransactionService transactionService;

    public ApiController(AccountService accountService, UserService userService, TransactionService transactionService){
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    ///////////////////////////////////////////////////
//    public static class Cont
//    {
//        public String IBAN;
//
//        public Cont(){
//            this.IBAN = "HU93116000060000000012345676";
//        }
//    }
//    public static class Tranzactie
//    {
//        public String nume;
//        public double suma;
//        public String numeComerciant;
//
//        public Tranzactie(){
//            this.suma = 234.23;
//            this.nume = "Tranzactie";
//            this.numeComerciant = "Afgan SRL";
//        }
//    }
    //private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    ///////////////////////////////////////////////////
//
//    public static int ok = 0;

    @PostMapping()
    @ResponseBody
    public List<Transaction> getTranzactii() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user= userService.getByEmail(userEmail);
        System.out.println(userEmail);
        List<Account> accounts = accountService.getAccountByUser(user);
        List<Transaction> transactions = new ArrayList<>();
        for(Account account : accounts){
            System.out.println(account.getIBAN());
            transactions.addAll(transactionService.findAllPendingTransactionsByAccount(account));
        }
        System.out.println("No of trans" + transactions.size());

        return transactions;
    }

    @PostMapping("/acceptaTranzactie/{id}")
    public String acceptTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.findById(id).orElse(null);
        if(!accountService.checkForAvailableAmount(transaction.getCatre().getId(), transaction.getValoare())){
            System.out.println("NU onorati tranzactia");
        }else{
            transaction.getCatre().setSold(transaction.getCatre().getSold() - transaction.getValoare());
        }
        transaction.setStatus("completed");
        return "redirect:/";
    }

    @PostMapping("/respingeTranzactie/{id}")
    public String rejectTransaction(@PathVariable Long id){
        transactionService.deleteTransactionById(id);
        return "redirect:/";
    }


//    @PostMapping("/api/tranzactii")
//    public List<Tranzactie> getTranzactii() {
//        //logger.info("Request POST primit la /api/tranzactii");
//        //ok++;
//        List<Tranzactie> tranzactii = new ArrayList<>();
//
//        //for(int i = 0; i<= ok; i++)
//        tranzactii.add(new Tranzactie());
//        tranzactii.add(new Tranzactie());
//
//        return tranzactii;
//    }

//    @PostMapping("/api/conturi-bancare")
//    public List<Cont> getConturiBancare() {
//        //logger.info("Request POST primit la /api/tranzactii");
//
//        List<Cont> conturi = new ArrayList<>();
//        conturi.add(new Cont());
//        conturi.add(new Cont());
//
//        return conturi;
//    }

}