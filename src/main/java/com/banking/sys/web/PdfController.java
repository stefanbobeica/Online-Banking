package com.banking.sys.web;
import com.banking.sys.model.Account;
import com.banking.sys.service.AccountServiceImpl;
import com.banking.sys.service.ExtrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class PdfController {
    @Autowired
    ExtrasService extrasService;
    @Autowired
    AccountServiceImpl accountService;

    @GetMapping("/contBancar/{accountId}/creareExtras")
    public ResponseEntity<InputStreamResource> creareExtras(@PathVariable Long accountId) throws IOException {
        Account cont = accountService.getAccountById(accountId);
        extrasService.createPdf(cont.getIBAN());
        String fileName = "Extras.pdf";
        FileInputStream file = new FileInputStream(fileName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(file));
    }
}
