package ro.msg.learning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.dto.ProductDto;
import ro.msg.learning.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products/{id}")
	public ProductDto get(@PathVariable Integer id) {
		return new ProductDto(productService.get(id));
	}

	@GetMapping("/products")
	public List<ProductDto> get() {
		List<ProductDto> products = new ArrayList<>();
		productService.getAll().forEach(currentProduct -> products.add(new ProductDto(currentProduct)));
		return products;
	}

	@PostMapping("/products")
	public ProductDto create(@RequestBody ProductDto product) {
		return new ProductDto(productService.save(product.toEntity()));
	}

	@PutMapping("/products")
	public ProductDto update(@RequestBody ProductDto product) {
		return new ProductDto(productService.save(product.toEntity()));
	}

	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id) {
		productService.delete(id);
	}
}
