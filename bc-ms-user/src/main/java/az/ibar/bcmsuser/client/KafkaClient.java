package az.ibar.bcmsuser.client;

import az.ibar.bcmsuser.model.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bc-ms-notification",url="http://${HOST_IP:localhost}:8004")
public interface KafkaClient {

    @PostMapping("/kafkaProducer")
    void sendVerificationEmailToKafka(@RequestBody NotificationDTO notificationDTO);

}
