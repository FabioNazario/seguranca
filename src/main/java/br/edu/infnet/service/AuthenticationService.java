package br.edu.infnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.infnet.client.UserClient;
import br.edu.infnet.model.User;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userClient.getByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		
		return user;

	}

}