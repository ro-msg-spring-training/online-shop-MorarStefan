package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
