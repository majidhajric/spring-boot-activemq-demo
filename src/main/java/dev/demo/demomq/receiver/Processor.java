package dev.demo.demomq.receiver;

import dev.demo.demomq.model.DemoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Processor {

    @JmsListener(destination = "${spring.activemq.source}")
    @SendTo("${spring.activemq.destination}")
    public DemoMessage processMessage(DemoMessage message) {
        log.info("Received message: {} ", message);

        DemoMessage transformedMessage = new DemoMessage();
        transformedMessage.setTitle(message.getTitle().toUpperCase());
        transformedMessage.setMessage(message.getMessage().toUpperCase());

        return transformedMessage;
    }
}
