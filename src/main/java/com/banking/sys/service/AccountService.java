package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account getAccountById(Long id);
    Account saveAccount(Account account);

    void deleteAccountByIban(String iban);

    Account getAccountByIBAN(String iban_dest);

    Boolean checkForAvailableAmount(Long id, Double amount);

    @Transactional
    void withdrawFromAccount(Account account,Double amount);
    @Transactional

    void addMoneyToAccount(Account account, Double amount);

    void deleteAccountById(Long accountId);

    List<Account> getAccountByUser(User user);
}
