package tacos.rabitmq;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tacos.entity.Order;
import tacos.jms.OrderMessagingService;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate template;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void sendOrder(Order order) {
        template.convertAndSend("tacocloud.order", order, message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setHeader("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
