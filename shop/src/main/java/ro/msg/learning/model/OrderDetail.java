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

@Entity(name = "OrderDetail")
@Table(name = "order_detail", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey id;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product;
}
