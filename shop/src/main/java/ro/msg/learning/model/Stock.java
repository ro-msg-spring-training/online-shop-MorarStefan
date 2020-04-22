package ro.msg.learning.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Stock")
@Table(name = "stock", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Stock {

	@EmbeddedId
	private StockKey id;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product")
	private Product product;

	@ManyToOne
	@MapsId("locationId")
	@JoinColumn(name = "location")
	private Location location;
}
