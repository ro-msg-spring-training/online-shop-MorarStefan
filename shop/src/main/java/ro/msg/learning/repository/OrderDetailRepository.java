package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {

}
