package com.banking.sys.service;

import com.banking.sys.model.Card;

import java.util.List;

public interface CardService {
   public List<Card> getCardsByIBAN(String IBAN);
   public void deleteCard(String numarCard);
}
