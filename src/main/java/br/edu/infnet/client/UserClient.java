package br.edu.infnet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.infnet.model.User;
import br.edu.infnet.model.UserDTO;

@FeignClient(name="USER")
public interface UserClient {

	@RequestMapping("/{id}")
	User getById(@PathVariable Long id);
	
	@RequestMapping("/{id}")
	UserDTO getByIdWhoami(@PathVariable Long id);
	
	@RequestMapping("/search?username={username}")
	User getByUsername(@PathVariable String username);
	
}
