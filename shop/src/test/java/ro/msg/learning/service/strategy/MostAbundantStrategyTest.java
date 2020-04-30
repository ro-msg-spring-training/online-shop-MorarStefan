package ro.msg.learning.service.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class MostAbundantStrategyTest {

	@Mock
	private StockRepository stockRepositoryMock;

	@InjectMocks
	private MostAbundantStrategy mostAbundantStrategy;

	@Test
	public void testProcessOrderNoProductsException() {
		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(new HashSet<>());

		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			mostAbundantStrategy.processOrder(orderMock);
		});

		Mockito.verify(orderMock, Mockito.times(1)).getOrderDetails();
		Mockito.verifyNoMoreInteractions(orderMock);
	}

	@Test
	public void testProcessOrderSuccessful() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 2, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 3, null, null));

		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(orderDetails);

		List<Stock> stockForProduct1 = new ArrayList<>();
		stockForProduct1.add(new Stock(new StockKey(1, 1), 5, null, null));
		stockForProduct1.add(new Stock(new StockKey(1, 2), 6, null, null));
		Mockito.when(stockRepositoryMock.findByProductId(1)).thenReturn(stockForProduct1);

		List<Stock> stockForProduct2 = new ArrayList<>();
		stockForProduct2.add(new Stock(new StockKey(1, 1), 9, null, null));
		stockForProduct2.add(new Stock(new StockKey(1, 2), 2, null, null));
		Mockito.when(stockRepositoryMock.findByProductId(2)).thenReturn(stockForProduct2);

		List<Stock> orderDistribution = mostAbundantStrategy.processOrder(orderMock);
		Assertions.assertEquals(2, orderDistribution.size());

		orderDistribution.forEach(distributedStock -> {
			if (distributedStock.getId().getProductId() == 1) {
				Assertions.assertEquals(2, distributedStock.getId().getLocationId());
				Assertions.assertEquals(2, distributedStock.getQuantity());
			} else if (distributedStock.getId().getProductId() == 2) {
				Assertions.assertEquals(1, distributedStock.getId().getLocationId());
				Assertions.assertEquals(3, distributedStock.getQuantity());
			} else {
				Assertions.fail("Unwanted product");
			}
		});

		Mockito.verify(orderMock, Mockito.times(2)).getOrderDetails();
		Mockito.verify(stockRepositoryMock, Mockito.times(1)).findByProductId(1);
		Mockito.verify(stockRepositoryMock, Mockito.times(1)).findByProductId(2);
	}

	@Test
	public void testProcessOrderUnsufficientStockException() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 2, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 3, null, null));

		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(orderDetails);

		List<Stock> stockForProduct1 = new ArrayList<>();
		stockForProduct1.add(new Stock(new StockKey(1, 1), 5, null, null));
		stockForProduct1.add(new Stock(new StockKey(1, 2), 6, null, null));
		Mockito.when(stockRepositoryMock.findByProductId(1)).thenReturn(stockForProduct1);

		List<Stock> stockForProduct2 = new ArrayList<>();
		stockForProduct2.add(new Stock(new StockKey(1, 1), 1, null, null));
		stockForProduct2.add(new Stock(new StockKey(1, 2), 2, null, null));
		Mockito.when(stockRepositoryMock.findByProductId(2)).thenReturn(stockForProduct2);

		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			mostAbundantStrategy.processOrder(orderMock);
		});

		Mockito.verify(orderMock, Mockito.times(2)).getOrderDetails();
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(1);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(2);
	}

	@Test
	public void testProcessOrderUnexistingProductException() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 1), 2, null, null));
		orderDetails.add(new OrderDetail(new OrderDetailKey(1, 2), 3, null, null));

		Order orderMock = Mockito.mock(Order.class);
		Mockito.when(orderMock.getOrderDetails()).thenReturn(orderDetails);

		List<Stock> stockForProduct1 = new ArrayList<>();
		Mockito.when(stockRepositoryMock.findByProductId(1)).thenReturn(stockForProduct1);

		List<Stock> stockForProduct2 = new ArrayList<>();
		stockForProduct2.add(new Stock(new StockKey(1, 1), 9, null, null));
		stockForProduct2.add(new Stock(new StockKey(1, 2), 2, null, null));
		Mockito.when(stockRepositoryMock.findByProductId(2)).thenReturn(stockForProduct2);

		Assertions.assertThrows(UnsuccessfulStrategyException.class, () -> {
			mostAbundantStrategy.processOrder(orderMock);
		});

		Mockito.verify(orderMock, Mockito.times(2)).getOrderDetails();
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(1);
		Mockito.verify(stockRepositoryMock, Mockito.atMost(1)).findByProductId(2);
	}
}
