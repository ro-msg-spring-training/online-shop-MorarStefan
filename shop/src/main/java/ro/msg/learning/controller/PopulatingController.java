package ro.msg.learning.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Address;
import ro.msg.learning.model.Customer;
import ro.msg.learning.model.Location;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailKey;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.StockKey;
import ro.msg.learning.model.Supplier;
import ro.msg.learning.service.OrderService;
import ro.msg.learning.service.PopulatingService;
import ro.msg.learning.service.ProductService;

@RestController
@RequiredArgsConstructor
@Profile("test")
public class PopulatingController {

	private final OrderService orderService;
	private final ProductService productService;
	private final PopulatingService populatingService;

	@PostMapping("/populateDatabase")
	public void populate() {
		clear();

		Product product = new Product(null, "P1", "Descr", BigDecimal.ONE, 1.0f, new ProductCategory(1, "C1", "Descr"),
				new Supplier(1, "S1"), "URL");
		Location location1 = new Location(null, "L1", new Address("Ro", "C-N", "Cluj", "Obs Street"));
		Location location2 = new Location(null, "L2", new Address("Ro", "C-N", "Cluj", "Garii Street"));

		Product productInDB = productService.save(product);
		
		populatingService.saveStock(new Stock(new StockKey(1, 1), 10, productInDB, populatingService.saveLocation(location1)));
		populatingService.saveStock(new Stock(new StockKey(1, 2), 20, productInDB, populatingService.saveLocation(location2)));

		Set<OrderDetail> orderDetails = new HashSet<>();
		orderDetails.add(new OrderDetail(new OrderDetailKey(null, 1), 2, null, null));

		orderService.save(new Order(null, null, new Customer(1, "John", "Doe", "jdoe", "1234", "john.doe@email.org"),
				LocalDateTime.now(), new Address("Romania", "Cluj-Napoca", "Cluj", "Obs Street"), orderDetails));
	}

	@PostMapping("/clearDatabase")
	public void clear() {
		orderService.deleteAll();
		populatingService.deleteAll();
		productService.deleteAll();
	}
}
