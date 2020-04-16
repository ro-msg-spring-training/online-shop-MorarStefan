package ro.msg.learning.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.OrderDetail;

@EntityScan("ro.msg.learning.model")

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
