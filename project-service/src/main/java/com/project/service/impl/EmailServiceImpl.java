package com.project.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${gmail.username}")
    private String username;

    @Value("${gmail.pw}")
    private String pw;

    @Value("${gmail.smtp.auth}")
    private String auth;

    @Value("${gmail.smtp.starttls.enable}")
    private String tls;

    @Value("${gmail.smtp.host}")
    private String host;

    @Value("${gmail.smtp.port}")
    private String port;

    @Value("${gmail.from}")
    private String from;

    @Value("${gmail.to}")
    private String to;

    @Value("${gmail.subject}")
    private String subject;

    @Value("${gmail.message}")
    private String text;

    @Override
    public void send(final String message) {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", tls);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        final Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pw);
            }
        });

        final Message m = new MimeMessage(session);
        try {
            m.setFrom(new InternetAddress(from));
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            m.setSubject(subject);

            final StringBuilder b = new StringBuilder(text);
            b.append(message);
            m.setText(b.toString());

            Transport.send(m);
        } catch (final MessagingException e) {
            log.error("Exception while sending email", e);
        }
    }
}