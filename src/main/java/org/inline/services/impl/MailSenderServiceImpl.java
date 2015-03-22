package org.inline.services.impl;

import org.apache.velocity.app.VelocityEngine;
import org.inline.entities.InlineUser;
import org.inline.services.AbstractInlineService;
import org.inline.services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service("mailSenderService")
public class MailSenderServiceImpl extends AbstractInlineService implements MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void sendMail(String to, String from, String text, String subject) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);

        mailSender.send(mail);

    }

    @Override
    public void sendRegistrationEmail(final InlineUser user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getUserData().getEmail());
                message.setSubject("Поздравляем с регистрацией на partnerconsulting.ru");
                message.setFrom("notreply.partnerconsulting@gmail.com");
                Map<String, Object> model = new HashMap<>();
                model.put("userName", user.getUsername());
                model.put("token", user.getToken());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "./templates/email-registration.vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

    @Override
    public void setPasswordReminder(final InlineUser user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getUserData().getEmail());
                message.setSubject("Востановление пароля");
                message.setFrom("notreply.partnerconsulting@gmail.com");
                Map<String, Object> model = new HashMap<>();
                model.put("userName", user.getUsername());
                model.put("token", user.getToken());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "./templates/email-password-reminder.vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

}
