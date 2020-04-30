package ro.msg.learning.service.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ro.msg.learning.model.Order;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailKey;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockKey;
import ro.msg.learning.repository.StockRepository;
import ro.msg.learning.service.strategy.exception.UnsuccessfulStrategyException;

@ExtendWith(MockitoExtension.class)
public class SingleLocationStrategyTest {

	@Mock
	private StockRepository stockRepositoryMock;

	@InjectMocks
	private SingleLocationStrategy singleLocationStrategy;

	@Test
	public void testProcessOrderNoProductsException() {
		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(new HashSet<>());

		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			singleLocationStrategy.processOrder(orderMock);
		});

		Mockito.verify(orderMock, Mockito.times(1)).getOrderDetails();
		Mockito.verifyNoMoreInteractions(orderMock);
	}

	@Test
	public void testProcessOrderSuccessful() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 2, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 1, null, null));

		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(orderDetails);

		List<Stock> stock = new ArrayList<>();
		stock.add(new Stock(new StockKey(1, 1), 5, null, null));

		Mockito.lenient().when(stockRepositoryMock.findByProductId(1)).thenReturn(stock);
		Mockito.lenient().when(stockRepositoryMock.findByProductId(2)).thenReturn(stock);

		Mockito.when(stockRepositoryMock.findById(new StockKey(1, 1)))
				.thenReturn(Optional.of(new Stock(new StockKey(1, 1), 4, null, null)));
		Mockito.when(stockRepositoryMock.findById(new StockKey(2, 1)))
				.thenReturn(Optional.of(new Stock(new StockKey(2, 1), 1, null, null)));

		List<Stock> orderDistribution = singleLocationStrategy.processOrder(orderMock);
		Assertions.assertEquals(2, orderDistribution.size());

		orderDistribution.forEach(distributedStock -> {
			Assertions.assertEquals(1, distributedStock.getId().getLocationId());
			if (distributedStock.getId().getProductId() == 1) {
				Assertions.assertEquals(2, distributedStock.getQuantity());
			} else if (distributedStock.getId().getProductId() == 2) {
				Assertions.assertEquals(1, distributedStock.getQuantity());
			} else {
				Assertions.fail("Unwanted product");
			}
		});

		Mockito.verify(orderMock, Mockito.times(4)).getOrderDetails();
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(1);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(2);
		Mockito.verify(stockRepositoryMock, Mockito.times(1)).findById(new StockKey(1, 1));
		Mockito.verify(stockRepositoryMock, Mockito.times(1)).findById(new StockKey(2, 1));
	}

	@Test
	public void testProcessOrderUnsufficientStockException() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 4, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 5, null, null));

		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(orderDetails);

		List<Stock> stock = new ArrayList<>();
		stock.add(new Stock(new StockKey(1, 1), 2, null, null));

		Mockito.lenient().when(stockRepositoryMock.findByProductId(1)).thenReturn(stock);
		Mockito.lenient().when(stockRepositoryMock.findByProductId(2)).thenReturn(stock);

		Mockito.lenient().when(stockRepositoryMock.findById(new StockKey(1, 1)))
				.thenReturn(Optional.of(new Stock(new StockKey(1, 1), 4, null, null)));
		Mockito.lenient().when(stockRepositoryMock.findById(new StockKey(2, 1)))
				.thenReturn(Optional.of(new Stock(new StockKey(2, 1), 1, null, null)));

		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			singleLocationStrategy.processOrder(orderMock);
		});

		Mockito.verify(orderMock, Mockito.atMost(4)).getOrderDetails();
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(1);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(2);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findById(new StockKey(1, 1));
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findById(new StockKey(2, 1));
	}

	@Test
	public void testProcessOrderUnexistingProductException() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 4, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 5, null, null));

		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(orderDetails);

		List<Stock> stock = new ArrayList<>();
		stock.add(new Stock(new StockKey(1, 1), 2, null, null));

		Mockito.lenient().when(stockRepositoryMock.findByProductId(1)).thenReturn(stock);
		Mockito.lenient().when(stockRepositoryMock.findByProductId(2)).thenReturn(stock);

		Mockito.lenient().when(stockRepositoryMock.findById(new StockKey(1, 1))).thenReturn(Optional.empty());
		Mockito.lenient().when(stockRepositoryMock.findById(new StockKey(2, 1)))
				.thenReturn(Optional.of(new Stock(new StockKey(2, 1), 1, null, null)));

		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			singleLocationStrategy.processOrder(orderMock);
		});

		Mockito.verify(orderMock, Mockito.atMost(4)).getOrderDetails();
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(1);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(2);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findById(new StockKey(1, 1));
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findById(new StockKey(2, 1));
	}
}
