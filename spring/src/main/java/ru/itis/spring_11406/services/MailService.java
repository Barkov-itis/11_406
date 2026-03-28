package ru.itis.spring_11406.services;

public interface MailService {
    void sendEmailForConfirm(String email, String code);
}
