package com.banking.sys.web;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import com.banking.sys.service.AccountService;
import com.banking.sys.service.ExtrasService;
import com.banking.sys.service.TransactionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/contBancar")
@SessionAttributes("accountId")
public class AccountController {
    private AccountService accountService;
    private TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public String contBancar(@PathVariable("id") Long id, Model model){

        Account account = accountService.getAccountById(id);
        model.addAttribute("cont", account);
        model.addAttribute("tranzactii", transactionService.findAllCompletedTransactionsByAccount(account)); //only completed transactions
        model.addAttribute("accountId", id);  // store the account id in the session
        return "contBancar";
    }

    @PostMapping("/initiereTranzactie")
    public String initTransaction(@RequestParam String suma, @RequestParam String iban,
                                  @ModelAttribute("accountId") Long accountId, Model model){

        Account account_source = accountService.getAccountById(accountId);
        Account account_dest = accountService.getAccountByIBAN(iban);
        Transaction transaction = new Transaction("intrabancar",account_dest ,Double.parseDouble(suma), LocalDateTime.now().toString(),account_source, "pending");
        if(!accountService.checkForAvailableAmount(accountId, Double.parseDouble(suma))){
            System.out.println("Not enough money");
        }else{
             accountService.withdrawFromAccount(account_source,Double.parseDouble(suma));
             accountService.addMoneyToAccount(account_dest,Double.parseDouble(suma));
             transaction.setStatus("completed");
             transactionService.save(transaction);

        }
        return "redirect:/contBancar/" + accountId;
    }
//
//    @PostMapping("/creareContBancar")
//    public String createBankAccount(@ModelAttribute Account account, Model model) {
//        Account savedAccount = accountService.saveAccount(account);
//        model.addAttribute("account", savedAccount);
//        return "somePageToShowTheAccount";
//    }

    @GetMapping("/{accountId}/stergereContBancar")
    public String stergereContBancar(@PathVariable Long accountId) {
        accountService.deleteAccountById(accountId);
        return "redirect:/conturiBancare/";
    }


}
