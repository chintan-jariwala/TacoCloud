package tacos.rabitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.entity.Order;

@Component
public class RabbitOrderReceiver {

    private final RabbitTemplate rabbitTemplate;

    private final MessageConverter converter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter converter) {
        this.rabbitTemplate = rabbitTemplate;
        this.converter = converter;
    }

    public Order receiveOrder() {

        return rabbitTemplate.receiveAndConvert("tacocloud.orders", new ParameterizedTypeReference<Order>() {
        });
    }
}
