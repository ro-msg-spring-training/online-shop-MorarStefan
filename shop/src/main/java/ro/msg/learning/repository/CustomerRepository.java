package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
