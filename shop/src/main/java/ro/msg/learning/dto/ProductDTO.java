package ro.msg.learning.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Supplier;

@Getter @Setter @NoArgsConstructor
public class ProductDTO {

	private int id;
	private String name;
	private String description;
	private BigDecimal price;
	private double weight;
	private String imageUrl;
	private String categoryName;
	private String categoryDescription;
	private String supplierName;

	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
		description = product.getDescription();
		price = product.getPrice();
		weight = product.getWeight();
		imageUrl = product.getImageUrl();
		categoryName = product.getCategory().getName();
		categoryDescription = product.getCategory().getDescription();
		supplierName = product.getSupplier().getName();
	}

	public Product toEntity() {
		return new Product(id, name, description, price, weight,
				new ProductCategory(null, categoryName, categoryDescription), new Supplier(null, supplierName),
				imageUrl);
	}
}
