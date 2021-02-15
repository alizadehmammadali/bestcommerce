package az.ibar.bcmsnotification.model;

import lombok.Data;

@Data
public class NotificationDTO {
    private String receiverName;
    private String receiverEmail;
    private String verificationUrl;
}
