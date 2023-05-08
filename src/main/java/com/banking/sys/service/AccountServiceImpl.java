package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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


}
