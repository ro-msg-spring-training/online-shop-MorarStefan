package ro.msg.learning.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Product;
import ro.msg.learning.model.ProductCategory;
import ro.msg.learning.model.Supplier;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

	public ProductDTO(Product product, ProductCategory category, Supplier supplier) {
		setFields(product, category, supplier);
	}

	private void setFields(Product product, ProductCategory category, Supplier supplier) {
		id = product.getId();
		name = product.getName();
		description = product.getDescription();
		price = product.getPrice();
		weight = product.getWeight();
		imageUrl = product.getImageUrl();
		categoryName = category.getName();
		categoryDescription = category.getDescription();
		supplierName = supplier.getName();
	}
}
