package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public void sendMessage(Transaction content) {

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, content.getUuid(), content);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", content, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message=[{}] due to : {}", content, ex.getMessage());
            }
        });
    }
}
