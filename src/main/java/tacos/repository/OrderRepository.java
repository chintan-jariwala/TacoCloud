package tacos.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.entity.Order;
import tacos.entity.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByZip(String zip);

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
