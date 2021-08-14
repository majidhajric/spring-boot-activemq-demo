package dev.demo.demomq.rest;

import dev.demo.demomq.model.DemoMessage;
import dev.demo.demomq.producer.JmsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/messages")
public class DemoController {

    @Value("${spring.activemq.destination}")
    private String destination;

    private final JmsProducer jmsProducer;

    private final JmsTemplate jmsTemplate;

    @GetMapping
    public DemoMessage getMessage() {
        return (DemoMessage) jmsTemplate.receiveAndConvert(destination);
    }

    @PostMapping
    public void createMessage(@RequestBody DemoMessage message) {
        jmsProducer.sendMessage(message);
    }
}
