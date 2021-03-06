package ro.msg.learning.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Customer;
import ro.msg.learning.model.Order;
import ro.msg.learning.model.OrderDetail;

@Getter @Setter @NoArgsConstructor
public class OrderDto {

	private static final Customer DEFAULT_CUSTOMER = new Customer(1, "John", "Doe", "jdoe", "1234", "john.doe@email.org");

	private LocalDateTime createdAt;
	private AddressDto address;
	private List<OrderDetailDto> products;

	public OrderDto(Order order) {
		createdAt = order.getCreatedAt();
		address = new AddressDto(order.getAddress());
		products = new ArrayList<>();
		order.getOrderDetails().forEach(product -> products.add(new OrderDetailDto(product)));
	}

	public Order toEntity() {
		return new Order(null, null, DEFAULT_CUSTOMER, createdAt, address.toEntity(), getOrderDetails());
	}

	private Set<OrderDetail> getOrderDetails() {
		Set<OrderDetail> orderDetails = new HashSet<>();
		products.forEach(product -> orderDetails.add(product.toEntity()));
		return orderDetails;
	}
}
