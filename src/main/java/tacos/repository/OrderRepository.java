package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByZip(String zip);
}
