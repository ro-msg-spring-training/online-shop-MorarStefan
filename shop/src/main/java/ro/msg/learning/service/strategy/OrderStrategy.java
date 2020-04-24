package ro.msg.learning.service.strategy;

import java.util.List;

import ro.msg.learning.model.Order;
import ro.msg.learning.model.Stock;

public interface OrderStrategy {

	List<Stock> processOrder(Order order);
}
