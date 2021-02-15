package az.ibar.bcmsnotification.services;

import az.ibar.bcmsnotification.model.NotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SendMailService sendMailService;

    @KafkaListener(id = "1", topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(NotificationDTO notificationDTO) {
        sendMailService.sendVerificationTokenToEmail(notificationDTO);
    }
}