package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.DemoProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class DemoController {
    private final DemoProducerService producerService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Transaction message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka topic");
    }

}
