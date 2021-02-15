package az.ibar.bcmsnotification.services;


import az.ibar.bcmsnotification.model.NotificationDTO;

public interface SendMailService {
    void sendVerificationTokenToEmail(NotificationDTO notificationDTO);
}
