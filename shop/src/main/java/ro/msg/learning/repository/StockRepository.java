package ro.msg.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockKey;

public interface StockRepository extends JpaRepository<Stock, StockKey> {

	List<Stock> findByProductId(Integer productId);
	
	List<Stock> findByLocationId(Integer locationId);
}
