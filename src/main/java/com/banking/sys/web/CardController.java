package com.banking.sys.web;

import com.banking.sys.model.Account;
import com.banking.sys.service.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardController {
@Autowired
    CardServiceImpl cardService;

    @PostMapping("/creareCard")
    public String creareCard(@RequestParam("selectedIban") String selectedIban, Model model) {
        System.out.println(selectedIban);

        cardService.creareCard(selectedIban);

        return "redirect:/carduri";
    }

    @PostMapping("/deleteCard")
    public String deleteCard(@RequestParam("numarCard") String numarCard, Model model) {
        cardService.deleteCard(numarCard);

        return "redirect:/carduri";
    }
}
