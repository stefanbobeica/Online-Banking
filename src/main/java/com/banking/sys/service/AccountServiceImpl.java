package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import com.banking.sys.model.User;
import com.banking.sys.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    private TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public List<Account> getAll() {
       return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> optionalCont = accountRepository.findById(id);
        Account account = optionalCont.orElse(null);
        return account;
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
    @Override
    public void deleteAccountByIban(String iban) {
        accountRepository.deleteByIban(iban);
    }

    @Override
    public Account getAccountByIBAN(String iban_dest) {
        return accountRepository.getByIban(iban_dest);
    }

    @Override
    public Boolean checkForAvailableAmount(Long id, Double amount) {
        return accountRepository.findAccountById(id).getSold().compareTo(amount) >= 0;
    }

    @Transactional
    @Override
    public void withdrawFromAccount(Account account, Double amount) {
        account.setSold(account.getSold() - amount);
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void addMoneyToAccount(Account account, Double amount) {
        account.setSold(account.getSold() + amount);
        accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long accountId) {
        Account account = this.getAccountById(accountId);
        Account deletedAccount  = new Account("deleted-account", 0.0);
        this.saveAccount(deletedAccount);
        List<Transaction> received = account.getReceivedTransactions()
                .stream()
                .map(transaction -> {
                    transaction.setCatre(deletedAccount);
                    return transaction;
                })
                .collect(Collectors.toList());
        for(Transaction transaction : received){
            transactionService.save(transaction);
        }
        List<Transaction> sent = account.getSentTransactions()
                .stream()
                .map(transaction -> {
                    transaction.setCatre(deletedAccount);
                    return transaction;
                })
                .collect(Collectors.toList());
        for(Transaction transaction : sent){
            transactionService.save(transaction);
        }
        accountRepository.deleteById(accountId);
    }

    @Override
    public List<Account> getAccountByUser(User user) {
        return accountRepository.findAccountsByUser(user);
    }


}
