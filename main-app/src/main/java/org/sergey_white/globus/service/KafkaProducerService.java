package org.sergey_white.globus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String MAIL_SENDER_TOPIC = "mail-sender";

    public void sendEmail(String email) {
        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(MAIL_SENDER_TOPIC, email);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent email to mail-sender topic: {}, offset: {}",
                        email, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send email to mail-sender topic: {} due to: {}",
                        email, ex.getMessage());
            }
        });
    }
}