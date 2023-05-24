package com.banking.sys.service;

import com.banking.sys.model.User;
import com.banking.sys.model.Vault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VaultService {
   public List<Vault> getAllVaultsByUser(User user);
   public Vault getVaultById(Long id);
   public void creareVault(String nume, User user);
   @Transactional
   public void stergereVaultById(Long id);
   public void adaugaFonduri(Long id,Double suma,String IBAN);
   public void retragereFonduri(Long id,Double suma,String IBAN);
}
