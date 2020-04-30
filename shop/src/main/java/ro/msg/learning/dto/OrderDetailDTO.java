package ro.msg.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.OrderDetail;
import ro.msg.learning.model.OrderDetailKey;

@Getter @Setter @NoArgsConstructor
public class OrderDetailDto {

	private Integer productId;
	private int quantity;

	public OrderDetailDto(OrderDetail orderDetail) {
		productId = orderDetail.getId().getProductId();
		quantity = orderDetail.getQuantity();
	}

	public OrderDetail toEntity() {
		return new OrderDetail(new OrderDetailKey(null, productId), quantity, null, null);
	}
}
