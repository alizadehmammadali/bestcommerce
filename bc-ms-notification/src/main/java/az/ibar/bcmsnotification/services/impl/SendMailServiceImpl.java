package az.ibar.bcmsnotification.services.impl;

import az.ibar.bcmsnotification.config.MailConfig;
import az.ibar.bcmsnotification.model.NotificationDTO;
import az.ibar.bcmsnotification.services.SendMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    @Value("${spring.mail.username}")
    private String hostMail;

    private final MailConfig mailConfig;


    @Override
    public void sendVerificationTokenToEmail(NotificationDTO notificationDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(hostMail);
        message.setTo(notificationDTO.getReceiverEmail());
        message.setSubject("Please verify your email address");
        message.setText("Hello , " + notificationDTO.getReceiverName() + ". Thank you for registering in our application. " +
                "Please verify your email address " + notificationDTO.getVerificationUrl());
        mailConfig.getJavaMailSender().send(message);
        log.info("Mail has been sent to {}", notificationDTO.getReceiverEmail());
    }
}
