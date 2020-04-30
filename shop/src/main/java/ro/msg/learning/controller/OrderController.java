package ro.msg.learning.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.dto.OrderDto;
import ro.msg.learning.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/orders")
	public OrderDto create(@RequestBody OrderDto order) {
		return new OrderDto(orderService.save(order.toEntity()));
	}
}
