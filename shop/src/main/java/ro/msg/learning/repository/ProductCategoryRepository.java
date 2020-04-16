package ro.msg.learning.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.ProductCategory;

@EntityScan("ro.msg.learning.model")

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}