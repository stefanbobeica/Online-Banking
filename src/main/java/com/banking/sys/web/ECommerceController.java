package com.banking.sys.web;

import com.banking.sys.service.ECommerceService;
import com.banking.sys.service.ECommerceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ECommerceController {

    ECommerceService eCommerceService;

    public ECommerceController(ECommerceService eCommerceService) {
        this.eCommerceService = eCommerceService;
    }

    @GetMapping("/initiazaTranzactie/{iban}")
    public String initiazaTranzactie(@PathVariable String iban){
        // logica pentru initiere tranzactie
        eCommerceService.askForMoney(iban);

        // redirecționează către o pagină după finalizarea tranzacției
        return "comerciant_fantoma";
    }


}
