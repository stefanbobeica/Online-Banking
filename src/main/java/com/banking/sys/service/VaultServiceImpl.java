package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import com.banking.sys.model.User;
import com.banking.sys.model.Vault;
import com.banking.sys.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class VaultServiceImpl implements  VaultService {
    @Autowired
    VaultRepository vaultRepository;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    TransactionServiceImpl transactionService;

    @Override
    public List<Vault> getAllVaultsByUser(User user){
        return  vaultRepository.findVaultsByUser(user);
    }

    @Override
    public Vault getVaultById(Long id){
        return vaultRepository.findVaultById(id);
    }

    @Override
    public void creareVault(String nume, User user){
        Vault depozit = new Vault();
        depozit.setNumeVault(nume);
        depozit.setSold(0d);
        depozit.setUser(user);
        vaultRepository.save(depozit);
    }

    @Override
    public void stergereVaultById(Long id){
        vaultRepository.deleteVaultById(id);
    }

    @Override
    public  void adaugaFonduri(Long id,Double suma,String IBAN){
       Account cont = accountService.getAccountByIBAN(IBAN);
       if (cont.getSold()>suma){
       accountService.withdrawFromAccount(cont,suma);
       Vault depozit = vaultRepository.getById(id);
       depozit.adauagaBani(suma);
        Transaction tranzactie = new Transaction("Alimentare depozit",cont,suma, LocalDate.now().toString(),cont,"completed");
        transactionService.save(tranzactie);}
    }

    @Override
    public  void retragereFonduri(Long id,Double suma,String IBAN){
        Account cont = accountService.getAccountByIBAN(IBAN);


        Vault depozit = vaultRepository.getById(id);
        if(depozit.getSold() >= suma){
        accountService.addMoneyToAccount(cont,suma);
        depozit.retrageBani(suma);
        Transaction tranzactie = new Transaction("Alimentare cont de la depozit",cont,suma, LocalDate.now().toString(),cont,"completed");
        transactionService.save(tranzactie);
        }
    }
}
