package com.banking.sys.repository;

import com.banking.sys.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByNumarCard(String numarCard);
    @Transactional
    void deleteCardByNumarCard(String numarCard);
    List<Card> getCardsByAccount_Iban(String IBAN);


}
