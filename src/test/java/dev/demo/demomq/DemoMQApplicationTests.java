package dev.demo.demomq;

import dev.demo.demomq.model.DemoMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.zapodot.junit5.jms.EmbeddedJmsBroker;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(EmbeddedJmsBroker.class)
@SpringBootTest
class DemoMQApplicationTests {

	@Value("${spring.activemq.source}")
	private String source;

	@Value("${spring.activemq.destination}")
	private String destination;

	@Autowired
	JmsTemplate jmsTemplate;

	@Test
	void processorTest() {
		DemoMessage message = new DemoMessage();
		message.setTitle("Test title");
		message.setMessage("Test message");

		jmsTemplate.convertAndSend(source, message);

		DemoMessage msg = (DemoMessage) jmsTemplate.receiveAndConvert(destination);

		assertEquals(message.getTitle().toUpperCase(), msg.getTitle());
		assertEquals(message.getMessage().toUpperCase(), msg.getMessage());
	}

}
