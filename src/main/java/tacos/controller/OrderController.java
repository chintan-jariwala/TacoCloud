package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.entity.Order;
import tacos.jms.OrderMessagingService;
import tacos.repository.OrderRepository;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;

    private final OrderMessagingService orderMessagingService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderMessagingService orderMessagingService) {
        this.orderRepository = orderRepository;
        this.orderMessagingService = orderMessagingService;
    }

    @GetMapping
    public Iterable<Order> allOrders() {
        return orderRepository.findAll();
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        orderMessagingService.sendOrder(order);
        return orderRepository.save(order);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {

        Optional<Order> orderOptional =
                orderRepository.findById(orderId);
        Order order;
        if (orderOptional.isPresent()) {
            order = orderOptional.get();
        } else {
            return null;
        }
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getState() != null) {
            order.setState(patch.getState());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            log.error("Empty Result access {}", e);
        }
    }
}
