package ro.msg.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.msg.learning.model.Customer;

@Getter @Setter @NoArgsConstructor
public class UserDetailsDto {

	private String username;
	private String password;
	
	public UserDetailsDto(Customer customer) {
		username = customer.getUsername();
		username = customer.getPassword();
	}
	
}
