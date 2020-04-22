package ro.msg.learning.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.msg.learning.dto.ProductDTO;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Stock;
import ro.msg.learning.model.Supplier;
import ro.msg.learning.repository.ProductCategoryRepository;
import ro.msg.learning.repository.ProductRepository;
import ro.msg.learning.repository.SupplierRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	public ProductDTO get(Integer id) {
		Product product = productRepository.findById(id).get();
		return new ProductDTO(product, product.getCategory(), product.getSupplier());
	}

	public List<ProductDTO> get() {
		List<ProductDTO> productsDTO = new ArrayList<>();
		productRepository.findAll().forEach(p -> productsDTO.add(new ProductDTO(p, p.getCategory(), p.getSupplier())));
		return productsDTO;
	}

	public ProductDTO save(ProductDTO productDTO) {
		ProductCategory category = getCategory(productDTO);
		Supplier supplier = getSupplier(productDTO);

		Product product = new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(),
				productDTO.getPrice(), productDTO.getWeight(), category, supplier, productDTO.getImageUrl(),
				new HashSet<Stock>(), new HashSet<OrderDetail>());
		productRepository.save(product);

		return new ProductDTO(product, category, supplier);
	}

	private ProductCategory getCategory(ProductDTO productDTO) {
		ProductCategory categoryInDB = categoryRepository.findByName(productDTO.getCategoryName());

		if (categoryInDB == null) {
			ProductCategory category = new ProductCategory(null, productDTO.getCategoryName(),
					productDTO.getCategoryDescription(), new HashSet<Product>());
			categoryRepository.save(category);
			return category;
		}
		ProductCategory category = new ProductCategory(categoryInDB.getId(), productDTO.getCategoryName(),
				productDTO.getCategoryDescription(), new HashSet<Product>());
		categoryRepository.save(category);
		return category;
	}

	private Supplier getSupplier(ProductDTO productDTO) {
		Supplier supplierInDB = supplierRepository.findByName(productDTO.getSupplierName());

		if (supplierInDB == null) {
			Supplier supplier = new Supplier(null, productDTO.getSupplierName(), new HashSet<>());
			supplierRepository.save(supplier);
			return supplier;
		}
		return supplierInDB;
	}

	public void delete(Integer id) {
		productRepository.deleteById(id);
	}
}
