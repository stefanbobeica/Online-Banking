package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllTransactionsByAccount(Account account);
}
