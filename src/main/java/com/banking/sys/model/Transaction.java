package com.banking.sys.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tip;
    @ManyToOne
    @JoinColumn(name = "catre_IBAN")
    private Account catre;
    private double valoare;
    private String data;
    @ManyToOne
    @JoinColumn(name = "deLa_IBAN")
    private Account deLa;
    private String status; //completed / pending

    public Transaction() {
    }

    public Transaction(/*Long id, */String tip, Account catre, double valoare, String data, Account deLa, String status) {
       // this.id = id;
        this.tip = tip;
        this.catre = catre;
        this.valoare = valoare;
        this.data = data;
        this.deLa = deLa;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }



    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Account getCatre() {
        return catre;
    }

    public void setCatre(Account catre) {
        this.catre = catre;
    }

    public Account getDeLa() {
        return deLa;
    }

    public void setDeLa(Account deLa) {
        this.deLa = deLa;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String send) {
        this.status = send;
    }
}
