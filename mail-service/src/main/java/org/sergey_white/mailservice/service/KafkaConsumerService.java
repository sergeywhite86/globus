package org.sergey_white.mailservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "mail-sender", groupId = "mail-service-group")
    public void consumeEmail(String email) {
        log.info("=== MAIL-SENDER TOPIC MESSAGE RECEIVED ===");
        log.info("Email received: {}", email);
        log.info("Would send welcome email to: {}", email);
        log.info("=== END OF MESSAGE ===");
    }

}