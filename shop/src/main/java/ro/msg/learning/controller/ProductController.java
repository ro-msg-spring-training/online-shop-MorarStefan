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
import ro.msg.learning.controller.exception.ProductNotFoundException;
import ro.msg.learning.dto.ProductDTO;
import ro.msg.learning.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products/{id}")
	public ProductDTO get(@PathVariable Integer id) {
		if (productService.get(id) == null) {
			throw new ProductNotFoundException(id);
		}
		return new ProductDTO(productService.get(id));
	}

	@GetMapping("/products")
	public List<ProductDTO> get() {
		List<ProductDTO> products = new ArrayList<>();
		productService.get().forEach(currentProduct -> products.add(new ProductDTO(currentProduct)));
		return products;
	}

	@PostMapping("/products")
	public ProductDTO create(@RequestBody ProductDTO product) {
		return new ProductDTO(productService.save(product.toEntity()));
	}

	@PutMapping("/products")
	public ProductDTO update(@RequestBody ProductDTO product) {
		return new ProductDTO(productService.save(product.toEntity()));
	}

	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id) {
		productService.delete(id);
	}
}
