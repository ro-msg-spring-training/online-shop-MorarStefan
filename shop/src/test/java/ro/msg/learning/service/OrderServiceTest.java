package ro.msg.learning.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ro.msg.learning.model.Address;
import ro.msg.learning.model.Customer;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailKey;
import ro.msg.learning.repository.OrderDetailRepository;
import ro.msg.learning.repository.OrderRepository;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.strategy.OrderStrategyFactory.Strategy;
import ro.msg.learning.service.strategy.exception.UnsuccessfulStrategyException;
import ro.msg.learning.shop.ShopApplication;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ShopApplication.class)
@ActiveProfiles("test")
public class OrderServiceTest {

	@Autowired
	public OrderService orderService;

	@Autowired
	public StockRepository stockRepository;

	@Autowired
	public OrderDetailRepository orderDetailRepository;

	@Autowired
	public OrderRepository orderRepository;

	@Value("${order-strategy}")
	private Strategy orderStrategy;

	private void testStockBeforeOrdering() {
		stockRepository.findByProductId(1).forEach(stock -> {
			if (stock.getId().getLocationId() == 1) {
				Assertions.assertEquals(10, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 2) {
				Assertions.assertEquals(5, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 3) {
				Assertions.assertEquals(4, stock.getQuantity());
			} else {
				Assertions.fail();
			}
		});

		stockRepository.findByProductId(2).forEach(stock -> {
			if (stock.getId().getLocationId() == 1) {
				Assertions.assertEquals(1, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 2) {
				Assertions.assertEquals(11, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 4) {
				Assertions.assertEquals(3, stock.getQuantity());
			} else {
				Assertions.fail();
			}
		});
	}

	private void testStockAfterOrdering() {
		stockRepository.findByProductId(1).forEach(stock -> {
			if (stock.getId().getLocationId() == 1) {
				Assertions.assertEquals(8, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 2) {
				Assertions.assertEquals(5, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 3) {
				Assertions.assertEquals(4, stock.getQuantity());
			} else {
				Assertions.fail();
			}
		});

		stockRepository.findByProductId(2).forEach(stock -> {
			if (stock.getId().getLocationId() == 1) {
				Assertions.assertEquals(1, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 2) {
				Assertions.assertEquals(8, stock.getQuantity());
			} else if (stock.getId().getLocationId() == 4) {
				Assertions.assertEquals(3, stock.getQuantity());
			} else {
				Assertions.fail();
			}
		});
	}

	private void testOrderDetailInsertion(Integer orderId) {
		orderDetailRepository.findAll().forEach(orderDetail -> {
			if (orderDetail.getId().getOrderId() == orderId && orderDetail.getId().getProductId() == 1) {
				Assertions.assertEquals(2, orderDetail.getQuantity());
			} else if (orderDetail.getId().getOrderId() == orderId && orderDetail.getId().getProductId() == 2) {
				Assertions.assertEquals(3, orderDetail.getQuantity());
			} else {
				Assertions.fail();
			}
		});
	}

	private void testOrderInsertion(Integer orderId) {
		Optional<Order> orderInDB = orderRepository.findById(orderId);
		if (orderInDB.isEmpty()) {
			Assertions.fail("The new order was not inserted.");
		}
	}

	@Test
	public void testSaveOrderSuccessfullyMostAbundant() {
		Assertions.assertEquals(Strategy.MOST_ABUNDANT, orderStrategy);

		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 2, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 3, null, null));
		Order order = new Order(null, null, new Customer(1, "John", "Doe", "jdoe", "1234", "john.doe@email.org"),
				LocalDateTime.now(), new Address("Romania", "Brasov", "Brasov", "Saturn Street"), orderDetails);

		testStockBeforeOrdering();

		Order resultOrder = orderService.save(order);
		Assertions.assertEquals(2, resultOrder.getOrderDetails().size());

		resultOrder.getOrderDetails().forEach(orderDetail -> {
			if (orderDetail.getId().getProductId() == 1) {
				Assertions.assertEquals(2, orderDetail.getQuantity());
			} else if (orderDetail.getId().getProductId() == 2) {
				Assertions.assertEquals(3, orderDetail.getQuantity());
			} else {
				Assertions.fail("Unwanted product.");
			}
		});

		testStockAfterOrdering();
		testOrderDetailInsertion(resultOrder.getId());
		testOrderInsertion(resultOrder.getId());
	}

	@Test
	public void testSaveOrderInsufficientStockMostAbundant() {
		Assertions.assertEquals(Strategy.MOST_ABUNDANT, orderStrategy);

		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 20, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 30, null, null));
		Order order = new Order(null, null, new Customer(1, "John", "Doe", "jdoe", "1234", "john.doe@email.org"),
				LocalDateTime.now(), new Address("Romania", "Brasov", "Brasov", "Saturn Street"), orderDetails);

		testStockBeforeOrdering();
		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			orderService.save(order);
		});
		testStockBeforeOrdering();
	}

}
