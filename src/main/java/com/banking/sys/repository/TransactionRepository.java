package com.banking.sys.repository;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDeLaOrCatreAndStatus(Account deLa, Account catre, String status);

    List<Transaction> findByStatusAndCatre(String pending, Account catre);
}
