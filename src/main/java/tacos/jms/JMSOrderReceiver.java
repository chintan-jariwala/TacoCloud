package tacos.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.entity.Order;

@Component
public class JMSOrderReceiver implements OrderReceiver {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JMSOrderReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Order receive(Order order) {
        return (Order) jmsTemplate.receiveAndConvert("tacocloud.order.queue");

    }

    @Override
    public Order receiveOrder() {
        return null;
    }

//    @Override
//    public Order receiveOrder(Order order) {
//        return null;
//    }
}
