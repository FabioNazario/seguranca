package br.edu.infnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.infnet.model.User;

@Service
public class AuthenticationService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			return restTemplate.getForObject("http://localhost:8081/api/user/search?username=" + username, User.class);
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}

	}

}