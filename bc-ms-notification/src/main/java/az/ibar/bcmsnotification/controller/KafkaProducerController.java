package az.ibar.bcmsnotification.controller;

import az.ibar.bcmsnotification.model.NotificationDTO;
import az.ibar.bcmsnotification.services.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafkaProducer")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<String> sendData(@RequestBody NotificationDTO notificationDTO){
        kafkaProducer.sendData(notificationDTO);
        return new ResponseEntity<>("Data sent to Kafka", HttpStatus.OK);
    }
}