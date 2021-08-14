package dev.demo.demomq.producer;

import dev.demo.demomq.model.DemoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JmsProducer {

    @Value("${spring.activemq.source}")
    private String source;

    private final JmsTemplate jmsTemplate;

    public void sendMessage(DemoMessage message){
        try{
            log.info("Attempting Send message to destination: "+ source);
            jmsTemplate.convertAndSend(source, message);
        } catch(Exception e){
            log.error("Received Exception during send Message: ", e);
        }
    }
}
