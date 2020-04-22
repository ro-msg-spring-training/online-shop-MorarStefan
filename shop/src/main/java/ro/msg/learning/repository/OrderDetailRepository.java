package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailKey;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {

}
