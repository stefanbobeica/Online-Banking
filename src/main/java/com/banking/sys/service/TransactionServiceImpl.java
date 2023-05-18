package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import com.banking.sys.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAllCompletedTransactionsByAccount(Account account) {

        return transactionRepository.findByDeLaOrCatreAndStatus(account, account, "completed");
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllPendingTransactionsByAccount(Account account) {
        return transactionRepository.findByStatusAndCatre("pending", account);
    }

    @Override
    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }
}
