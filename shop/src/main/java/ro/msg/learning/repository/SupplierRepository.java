package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	Supplier findByName(String name);
}
