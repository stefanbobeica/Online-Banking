package com.banking.sys.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String IBAN;
    private Double sold;

    @OneToMany(mappedBy = "deLa")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "catre")
    private List<Transaction> receivedTransactions;

    public Account() {
    }

    public Account(Long id, String IBAN, Double sold) {
        this.id = id;
        this.IBAN = IBAN;
        this.sold = sold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }
}
