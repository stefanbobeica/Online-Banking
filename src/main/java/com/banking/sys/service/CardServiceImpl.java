package com.banking.sys.service;
import java.util.Random;
import com.banking.sys.model.Account;
import com.banking.sys.model.Card;
import com.banking.sys.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class CardServiceImpl implements  CardService{
    @Autowired
    CardRepository cardRepository;
    @Autowired
    AccountServiceImpl accountService;

    public List<Card>  getCardsByIBAN(String IBAN) {
     return   cardRepository.getCardsByAccount_Iban(IBAN);
    }
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public void creareCard(String IBAN){
        Account cont = accountService.getAccountByIBAN(IBAN);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(1 + random.nextInt(9));
        for (int i = 0; i < 15; i++) {
            sb.append(random.nextInt(10));
        }
        String numarCard =sb.toString();
        Integer CCV = random.nextInt(900) + 100;
        LocalDate dataExpirare = LocalDate.now().plusYears(4);

        Card card = new Card(numarCard,CCV,dataExpirare,cont);
        saveCard(card);
    }

    @Override
    public void  deleteCard(String numarCard){
        cardRepository.deleteCardByNumarCard(numarCard);
    }

}
