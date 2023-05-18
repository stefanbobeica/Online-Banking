package com.banking.sys.web;

import com.banking.sys.model.Transaction;
import com.banking.sys.model.User;
import com.banking.sys.service.TransactionService;
import com.banking.sys.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.banking.sys.model.Account;
import com.banking.sys.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tranzactii")
public class ApiController {
    private AccountService accountService;
    private UserService userService;
    private TransactionService transactionService;

    public ApiController(AccountService accountService, UserService userService, TransactionService transactionService){
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping()
    public String getTranzactii(Model model) {
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
        model.addAttribute("transactions", transactions);
        return "tranzactii";
    }

    @PostMapping("/acceptaTranzactie/{id}")
    public String acceptTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.findById(id).get();
        System.out.println(transaction.getDeLa());
        if(!accountService.checkForAvailableAmount(transaction.getCatre().getId(), transaction.getValoare())){
            System.out.println("NU onorati tranzactia");
        }else{
            transaction.getCatre().setSold(transaction.getCatre().getSold() - transaction.getValoare());
        }
        transaction.setStatus("completed");
        transactionService.save(transaction);
        return "redirect:/tranzactii/";
    }

    @PostMapping("/respingeTranzactie/{id}")
    public String rejectTransaction(@PathVariable Long id){
        transactionService.deleteTransactionById(id);
        return "redirect:/tranzactii/";
    }




}