package tacos.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.entity.Order;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
@Slf4j
public class JMSOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jmsTemplate;


    @Autowired
    public JMSOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        log.debug(order.toString());
        jmsTemplate.convertAndSend(order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
