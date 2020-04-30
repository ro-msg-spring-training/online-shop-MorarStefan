package ro.msg.learning.service.strategy;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockKey;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.strategy.exception.UnsuccessfulStrategyException;

@RequiredArgsConstructor
public class MostAbundantStrategy implements OrderStrategy {

	private final StockRepository stockRepository;

	@Override
	public List<Stock> processOrder(Order order) {
		if (order.getOrderDetails().isEmpty()) {
			throw new UnsuccessfulStrategyException("No products");
		}

		List<Stock> productDistribution = new ArrayList<>();

		for (OrderDetail neededProduct : order.getOrderDetails()) {
			List<Stock> stocks = stockRepository.findByProductId(neededProduct.getId().getProductId());
			if(stocks.isEmpty()) {
				throw new UnsuccessfulStrategyException("Unsufficient quantity.");
			}
			
			int maxQuantity = stocks.get(0).getQuantity();
			Integer maxQuantityLocationId = stocks.get(0).getId().getLocationId();

			for (Stock stock : stocks) {
				if (maxQuantity < stock.getQuantity()) {
					maxQuantity = stock.getQuantity();
					maxQuantityLocationId = stock.getId().getLocationId();
				}
			}

			if (maxQuantity < neededProduct.getQuantity()) {
				throw new UnsuccessfulStrategyException("Unsufficient quantity.");
			}

			productDistribution.add(new Stock(new StockKey(neededProduct.getId().getProductId(), maxQuantityLocationId),
					neededProduct.getQuantity(), null, null));
		}
		return productDistribution;
	}
}
