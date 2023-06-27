package com.github.fabriciolfj.javaexamples.config;

import jakarta.mail.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfiguration {

    //Multipurpose Internet Mail Extensions (Extensões Multipropósito para Correio da Internet)

    private static final String MSG = """
                   Dear Administrator,
                   An error occurred when copying the following file :
                   Source directory: %s
                   Destination directory: %s
                   Filename : %s
            """;

    @Bean
    public JavaMailSenderImpl mailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(3025);
        mailSender.setUsername("system");
        mailSender.setPassword("12345");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage copyErrorMailMessage() {
        var message = new SimpleMailMessage();
        message.setFrom("system@localhost");
        message.setTo("admin@localhost");
        message.setSubject("File Copy Error");
        message.setText(MSG);
        return message;
    }
}
