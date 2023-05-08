package com.banking.sys.service;

import com.banking.sys.model.Account;
import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account getAccountById(Long id);
}
