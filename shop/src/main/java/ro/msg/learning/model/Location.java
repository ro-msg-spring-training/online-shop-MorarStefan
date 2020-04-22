package ro.msg.learning.model;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Location")
@Table(name = "location", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Location {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Embedded
	@AttributeOverrides({
	  @AttributeOverride( name = "country", column = @Column(name = "country")),
	  @AttributeOverride( name = "city", column = @Column(name = "city")),
	  @AttributeOverride( name = "county", column = @Column(name = "county")),
	  @AttributeOverride( name = "streetAddress", column = @Column(name = "street_address"))
	})
	private Address address;
	
	@OneToMany(mappedBy = "location")
	private Set<Revenue> revenues;
	
	@OneToMany(mappedBy = "shippedFrom")
	private Set<Order> orders;
	
	@OneToMany(mappedBy = "location")
	private Set<Stock> stocks;
}
