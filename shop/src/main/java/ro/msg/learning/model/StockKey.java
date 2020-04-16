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
public class StockKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "product", nullable = false)
	private int productId;

	@Column(name = "location", nullable = false)
	private int locationId;
}
