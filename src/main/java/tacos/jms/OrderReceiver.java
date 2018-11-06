package tacos.jms;

import tacos.entity.Order;

public interface OrderReceiver {

    Order receive(Order order);

    Order receiveOrder();
}
