package org.inline.services;


import org.inline.entities.InlineUser;

public interface MailSenderService {
    void sendMail(String to, String from, String text, String subject);
    void sendRegistrationEmail(InlineUser user);
    void setPasswordReminder(InlineUser user);
}
