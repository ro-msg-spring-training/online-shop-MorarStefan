package ro.msg.learning.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.controller.exception.OrderNotPossibleException;
import ro.msg.learning.dto.OrderDTO;
import ro.msg.learning.model.Order;
import ro.msg.learning.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/orders")
	public OrderDTO create(@RequestBody OrderDTO order) {
		Order serviceResponse = orderService.save(order.toEntity());
		if (serviceResponse == null) {
			throw new OrderNotPossibleException();
		}
		return new OrderDTO(serviceResponse);
	}
}
