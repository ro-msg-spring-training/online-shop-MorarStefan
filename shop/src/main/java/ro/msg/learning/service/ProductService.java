package ro.msg.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Supplier;
import ro.msg.learning.repository.ProductCategoryRepository;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.SupplierRepository;
import ro.msg.learning.service.exception.ProductNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductCategoryRepository categoryRepository;
	private final SupplierRepository supplierRepository;

	public Product get(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		throw new ProductNotFoundException(id);
	}

	public List<Product> getAll() {
		return productRepository.findAll(); 
	}

	@Transactional
	public Product save(Product product) {
		ProductCategory category = getUpdatedCategory(product.getCategory());
		Supplier supplier = getSupplier(product.getSupplier());
		
		product.setCategory(category);
		product.setSupplier(supplier);
		
		return productRepository.save(product);
	}

	private ProductCategory getUpdatedCategory(ProductCategory category) {
		ProductCategory categoryInDB = categoryRepository.findByName(category.getName());

		if (categoryInDB == null) {
			return categoryRepository.save(category);	
		}
		category.setId(categoryInDB.getId());
		categoryRepository.save(category);
		return category;
	}

	private Supplier getSupplier(Supplier supplier) {
		Supplier supplierInDB = supplierRepository.findByName(supplier.getName());

		if (supplierInDB == null) {
			return supplierRepository.save(supplier);
		}
		return supplierInDB;
	}

	public void delete(Integer id) {
		productRepository.deleteById(id);
	}
	
	public void deleteAll() {
		productRepository.deleteAll();
		categoryRepository.deleteAll();
		supplierRepository.deleteAll();
	}
}
