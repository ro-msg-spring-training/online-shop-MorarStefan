package ro.msg.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.Stock;
import ro.msg.learning.repository.LocationRepository;
import ro.msg.learning.repository.OrderDetailRepository;
import ro.msg.learning.repository.OrderRepository;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.strategy.OrderStrategyFactory;
import ro.msg.learning.service.strategy.exception.UnsuccessfulStrategyException;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final StockRepository stockRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final LocationRepository locationRepository;
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final OrderStrategyFactory orderStrategyFactory;

	@Transactional
	public Order save(Order order) {
		try {
			List<Stock> productDistribution = orderStrategyFactory.orderStrategy().processOrder(order);

			updateStocks(productDistribution);
			order.setShippedFrom(getLocationShippedFrom(productDistribution));
			Order createdOrder = orderRepository.save(order);
			insertOrderDetails(createdOrder);

			return createdOrder;
		} catch (UnsuccessfulStrategyException e) {
			return null;
		}
	}

	private void updateStocks(List<Stock> productDistribution) {
		productDistribution.forEach(orderStock -> {
			Stock currentStock = stockRepository.findById(orderStock.getId()).get();
			currentStock.setQuantity(currentStock.getQuantity() - orderStock.getQuantity());
			stockRepository.save(currentStock);
		});
	}

	private void insertOrderDetails(Order order) {
		order.getOrderDetails().forEach(orderDetail -> {
			orderDetail.getId().setOrderId(order.getId());
			orderDetail.setOrder(order);
			Product product = productRepository.findById(orderDetail.getId().getProductId()).get();
			orderDetail.setProduct(product);
			orderDetailRepository.save(orderDetail);
		});
	}

	private Location getLocationShippedFrom(List<Stock> productDistribution) {
		Integer locationId = productDistribution.get(0).getId().getLocationId();
		Optional<Location> location = locationRepository.findById(locationId);
		if (location.isPresent()) {
			return location.get();
		}
		return null;
	}
}
