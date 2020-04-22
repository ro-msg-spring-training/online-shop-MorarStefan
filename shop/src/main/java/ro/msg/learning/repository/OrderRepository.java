package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
