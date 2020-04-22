package ro.msg.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockKey;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockKey> {

}
