package dev.demo.demomq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@RequiredArgsConstructor
@EnableJms
@Configuration
public class ActiveMQConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setPubSubDomain(false);  // enable for Pub Sub to topic. Not Required for Queue.
        jmsTemplate.setReceiveTimeout(5000);
        return jmsTemplate;
    }
}
