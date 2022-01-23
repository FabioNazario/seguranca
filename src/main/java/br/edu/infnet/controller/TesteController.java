package br.edu.infnet.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.model.TokenDTO;

@RestController
@RequestMapping("/oba")
public class TesteController {
	
	@Autowired
	AuthenticationManager manager;

	@GetMapping
	public String oba(@RequestHeader("Authorization") String token) {
		
		System.out.println(manager.toString());

		return StringUtils.removeStart(token,"Bearer").trim();
	}
	
	@GetMapping("/bayblade")
	public ResponseEntity<TokenDTO> bayblade() {
		
		System.out.println(manager.toString());

		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token("Aquele token").build());
	}
	
}
