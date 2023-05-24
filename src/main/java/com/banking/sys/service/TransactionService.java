package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findAllCompletedTransactionsByAccount(Account account);

    void save(Transaction transaction);

    List<Transaction> findAllPendingTransactionsByAccount(Account account);


    void deleteTransactionById(Long id);

    Optional<Transaction> findById(Long id);
}
