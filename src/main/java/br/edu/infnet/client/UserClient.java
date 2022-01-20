package br.edu.infnet.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.infnet.model.User;

@FeignClient(name="USER")
public interface UserClient {

	@RequestMapping("/api/user/{id}")
	User getById(@PathVariable Long id);
	
	@RequestMapping("/api/user/search?username={username}")
	User getByUsername(@PathVariable String username);
	
}
