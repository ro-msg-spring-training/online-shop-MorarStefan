package ro.msg.learning.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.model.Customer;
import ro.msg.learning.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

	public final CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserBuilder builder = null;
		Customer customer = customerRepository.findByUsername(username);
		if(customer != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
		    builder.password(new BCryptPasswordEncoder().encode(customer.getPassword()));
		    builder.roles("USER");
		    return builder.build();
		}
		throw new UsernameNotFoundException(username + " not found.");
	}

}
