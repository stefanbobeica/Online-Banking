package com.banking.sys.repository;

import com.banking.sys.model.Account;
import com.banking.sys.model.User;
import com.banking.sys.model.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaultRepository  extends JpaRepository<Vault, Long>  {
   List<Vault> findVaultsByUser(User user);
   Vault findVaultById(Long id);
   void deleteVaultById(Long id);
}
