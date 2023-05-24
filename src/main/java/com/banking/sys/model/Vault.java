package com.banking.sys.model;

import javax.persistence.*;

@Entity
public class Vault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeVault;
    private Double sold;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeVault() {
        return numeVault;
    }

    public void setNumeVault(String numeVault) {
        this.numeVault = numeVault;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }
    public void adauagaBani(double suma){
        this.sold = this.sold+suma;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void retrageBani(double suma){
        if(this.sold - suma > 0)
        this.sold = this.sold-suma;
    }
}
