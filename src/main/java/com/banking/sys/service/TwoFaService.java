package com.banking.sys.service;

public interface TwoFaService {
    public void sendSimpleEmail(String to, String subject, String text);
}
