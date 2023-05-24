package com.banking.sys.web;

import com.banking.sys.model.Account;
import com.banking.sys.model.User;
import com.banking.sys.model.Vault;
import com.banking.sys.service.AccountServiceImpl;
import com.banking.sys.service.UserServiceImpl;
import com.banking.sys.service.VaultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class VaultController {
    @Autowired
    VaultServiceImpl vaultService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AccountServiceImpl accountService;

    @GetMapping("/vault/{id}")
    public String vaultInfo(@PathVariable("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user= userService.getByEmail(userEmail);
        List<Account> conturi = accountService.getAccountByUser(user);
        Vault vault = vaultService.getVaultById(id);
        model.addAttribute("conturi", conturi);
        model.addAttribute("vault", vault);

        return "vault";
    }

    @PostMapping("/deschidereVault")
    public String deschidereVault(@RequestParam String nume, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user= userService.getByEmail(userEmail);

        vaultService.creareVault(nume,user);
        return "redirect:vaults";
    }

    @PostMapping("/stergereVault")
    public String stergereVault(@RequestParam("idVault") Long idVault, Model model) {
        vaultService.stergereVaultById(idVault);
        return "redirect:/vaults";
    }

    @PostMapping("/adaugareFonduriVault")
    public String adaugareFonduriVault(@RequestParam("idVault") Long idVault,
                                       @RequestParam("suma") Double suma,
                                       @RequestParam("selectedIban") String selectedIban,
                                       Model model) {
        vaultService.adaugaFonduri(idVault,suma,selectedIban);



        return "redirect:vaults";
    }

    @PostMapping("/retragereFonduriVault")
    public String retragereFonduriVault(@RequestParam("idVault") Long idVault,
                                       @RequestParam("suma") Double suma,
                                       @RequestParam("selectedIban") String selectedIban,
                                       Model model) {
        vaultService.retragereFonduri(idVault,suma,selectedIban);
        return "redirect:vaults";
    }
}
