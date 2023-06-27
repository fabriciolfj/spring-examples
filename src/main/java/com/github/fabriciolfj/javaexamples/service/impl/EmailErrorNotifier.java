package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.service.ErrorNotifier;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailErrorNotifier implements ErrorNotifier {

    private final JavaMailSenderImpl mailSender;
    private final SimpleMailMessage template;


    @Override
    public void notifyCopyError(String srcDir, String destDir, String fileName) {
        MimeMessagePreparator preparator = (mimeMessage) -> {
            var helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(template.getFrom());
            helper.setTo(template.getTo());
            helper.setSubject(template.getSubject());
            helper.setText(String.format(
                    template.getText(), srcDir, destDir, fileName));
            helper.addAttachment("beans.xml", new ClassPathResource("beans.xml"));
        };
        mailSender.send(preparator);
    }
}
