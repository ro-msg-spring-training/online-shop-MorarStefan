package ro.msg.learning.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Address {
	
	private String country;
	private String city;
	private String county;
	
	@Column(name = "street_address")
	private String streetAddress;
}
