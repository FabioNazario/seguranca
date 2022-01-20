package br.edu.infnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.infnet.client.UserClient;
import br.edu.infnet.model.User;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userClient.getByUsername(username);

	}

}