package ro.msg.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.msg.learning.dto.ProductDTO;
import ro.msg.learning.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products/{id}")
	public ProductDTO get(@PathVariable Integer id) {
		return service.get(id);
	}
	
	@GetMapping("/products")
	public List<ProductDTO> get() {
		return service.get();
	}
	
	@PostMapping("/products")
	public ProductDTO create(@RequestBody ProductDTO product) {
		return service.save(product);
	}
	
	@PutMapping("/products")
	public ProductDTO update(@RequestBody ProductDTO product) {
		return service.save(product);
	}
		
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
