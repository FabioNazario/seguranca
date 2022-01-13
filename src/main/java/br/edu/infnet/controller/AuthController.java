package br.edu.infnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.model.LoginDTO;
import br.edu.infnet.model.TokenDTO;
import br.edu.infnet.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO loginDTO) {
		
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPass());
		
		Authentication authenticate = manager.authenticate(auth);
		String token = this.tokenService.generateToken(authenticate);
		
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
		
	}
}
