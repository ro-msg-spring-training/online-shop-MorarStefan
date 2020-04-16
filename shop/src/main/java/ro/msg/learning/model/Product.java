package ro.msg.learning.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Product")
@Table(name = "product")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "weight")
	private double weight;
	
	@ManyToOne
	@MapsId("category")
	@JoinColumn(name = "category")
	private ProductCategory category;
	
	@ManyToOne
	@MapsId("supplier")
	@JoinColumn(name = "supplier")
	private Supplier supplier;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy = "customer")
	private Set<Stock> stock;
	
	@OneToMany(mappedBy = "customer")
	private Set<OrderDetail> orderDetails;
}
