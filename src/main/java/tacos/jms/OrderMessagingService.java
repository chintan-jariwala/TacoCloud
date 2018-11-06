package tacos.jms;

import tacos.entity.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);
}
