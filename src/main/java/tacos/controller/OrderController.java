package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entity.Order;
import tacos.entity.User;
import tacos.repository.OrderRepository;
import tacos.repository.UserRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrderDetails(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                                      @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            log.error("Fill the shit completely {}", order.toString());
            return "orderForm";
        }
        order.setUser(user);

        log.info("Processing order {}", order.toString());
        orderRepository.save(order);

        sessionStatus.setComplete();
        return "redirect:/";
    }
}
