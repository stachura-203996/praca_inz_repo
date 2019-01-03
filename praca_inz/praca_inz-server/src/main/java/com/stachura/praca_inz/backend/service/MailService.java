package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;

/**
 * Interfejs do wysyłania emaili z powiadomieniami do użytkowników
 */
public interface MailService {

    /**
     * Metoda wysyłająca email po udanej rejestracji użytkownika
     *
     * @param to email na który ma zostać wysłana wiadomość
     * @throws ServiceException gdy wysłanie emaila nie powiedzie się
     */
    void sendRegisterMessage(String to) throws ServiceException;

    /**
     * Metoda wysyłająca email po udanej aktywacji konta użytkownika
     *
     * @param to email na który ma zostać wysłana wiadomość
     * @throws ServiceException gdy wysłanie emaila nie powiedzie się
     */
    void sendActivateAccountMessage(String to) throws ServiceException;

    /**
     * Metoda wysyłająca email po udanej dezaktywacji konta użytkownika
     *
     * @param to email na który ma zostać wysłana wiadomość
     * @throws ServiceException gdy wysłanie emaila nie powiedzie się
     */
    void sendDeactivateAccountMessage(String to) throws ServiceException;

    /**
     * Metoda wysyłająca email po udanym zweryfikowaniu użytkownika
     *
     * @param to email na który ma zostać wysłana wiadomość
     * @throws ServiceException gdy wysłanie emaila nie powiedzie się
     */
    void sendVerifyAccountMessage(String to) throws ServiceException;
}
