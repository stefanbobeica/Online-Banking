package com.banking.sys.repository;

import com.banking.sys.model.Account;
import com.banking.sys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();

    Account findAccountById(Long id);

    void deleteByIban(String iban);

    Account getByIban(String iban);
    List<Account> findAccountsByUser(User user);
    List<Account> findAccountsById(Long Id);
}
