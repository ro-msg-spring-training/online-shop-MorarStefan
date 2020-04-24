package ro.msg.learning.service.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockKey;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.strategy.exception.UnsuccessfulStrategyException;

@RequiredArgsConstructor
public class SingleLocationStrategy implements OrderStrategy {

	private final StockRepository stockRepository;

	@Override
	public List<Stock> processOrder(Order order) {
		if (order.getOrderDetails().isEmpty()) {
			throw new UnsuccessfulStrategyException("No products");
		}

		List<Stock> orderDistribution = new ArrayList<>();

		List<Integer> possibleLocations = getPossibleLocations(order);
		boolean haveFoundLocation;

		for (Integer locationId : possibleLocations) {
			haveFoundLocation = true;
			for (OrderDetail neededProduct : order.getOrderDetails()) {
				if (neededProduct.getQuantity() > findStockQuantity(neededProduct.getId().getProductId(), locationId)) {
					haveFoundLocation = false;
					break;
				}
			}
			if (haveFoundLocation) {
				for (OrderDetail needProduct : order.getOrderDetails()) {
					orderDistribution.add(new Stock(new StockKey(needProduct.getId().getProductId(), locationId),
							needProduct.getQuantity(), null, null));
				}
				break;
			}
		}

		if (orderDistribution.isEmpty()) {
			throw new UnsuccessfulStrategyException("Could not find all the products at a single location.");
		}
		return orderDistribution;
	}

	private List<Integer> getPossibleLocations(Order order) {
		Integer id = order.getOrderDetails().iterator().next().getId().getProductId();
		List<Stock> stocks = stockRepository.findByProductId(id);

		List<Integer> locationIds = new ArrayList<>();
		stocks.forEach(stock -> locationIds.add(stock.getId().getLocationId()));
		return locationIds;
	}

	private int findStockQuantity(Integer productId, Integer locationId) {
		Optional<Stock> stock = stockRepository.findById(new StockKey(productId, locationId));
		if (stock.isPresent()) {
			return stock.get().getQuantity();
		}
		return 0;
	}
}
