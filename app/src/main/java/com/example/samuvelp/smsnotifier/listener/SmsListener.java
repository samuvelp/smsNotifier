package com.example.samuvelp.smsnotifier.listener;

/**
 * Created by samuvelp on 26/01/18.
 */

public interface SmsListener {
    void messageReceived(String senderName, String messageText);
}
