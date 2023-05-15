package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import com.banking.sys.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllTransactionsByAccount(Account account) {

        return transactionRepository.findByDeLaOrCatre(account, account);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
