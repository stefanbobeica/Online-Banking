package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ECommerceServiceImpl implements ECommerceService {

    AccountService accountService;
    TransactionService transactionService;

    public ECommerceServiceImpl(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public void askForMoney(String iban){
        //System.out.println("asked for money");
        Account commerciant = new Account("comerciant1", 0d);
        accountService.saveAccount(commerciant);

        Transaction transaction = new Transaction("interbancara",accountService.getAccountByIBAN(iban) , 120.00, LocalDateTime.now().toString(),commerciant,"pending");
        transactionService.save(transaction);
    }

}
