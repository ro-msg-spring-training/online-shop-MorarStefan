package ro.msg.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Address;

@Getter @Setter @NoArgsConstructor
public class AddressDto {

	private String country;
	private String city;
	private String county;
	private String streetAddress;
	
	public AddressDto(Address address) {
		country = address.getCountry();
		city = address.getCity();
		county = address.getCounty();
		streetAddress = address.getStreetAddress();
	}
	
	public Address toEntity() {
		return new Address(country, city, county, streetAddress);
	}
}
