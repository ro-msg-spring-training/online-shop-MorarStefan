package ro.msg.learning.model;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Order")
@Table(name = "order")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Order {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@MapsId("shipped_from")
	@JoinColumn(name = "shipped_from")
	private Location shippedFrom;
	
	@ManyToOne
	@MapsId("customer")
	@JoinColumn(name = "customer")
	private Customer customer;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Embedded
	@AttributeOverrides({
	  @AttributeOverride( name = "country", column = @Column(name = "country")),
	  @AttributeOverride( name = "city", column = @Column(name = "city")),
	  @AttributeOverride( name = "county", column = @Column(name = "county")),
	  @AttributeOverride( name = "streetAddress", column = @Column(name = "street_address"))
	})
	private Address address;
}
