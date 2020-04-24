package ro.msg.learning.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class OrderDetailKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "order_id", nullable = false)
	private Integer orderId;

	@Column(name = "product_id", nullable = false)
	private Integer productId;
}
