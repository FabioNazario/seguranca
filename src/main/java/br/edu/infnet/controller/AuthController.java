package br.edu.infnet.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.client.UserClient;
import br.edu.infnet.model.LoginDTO;
import br.edu.infnet.model.Perfil;
import br.edu.infnet.model.TokenDTO;
import br.edu.infnet.model.User;
import br.edu.infnet.service.TokenService;
import lombok.Builder;

@RestController
@RequestMapping("/")
public class AuthController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	UserClient userClient;
	
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO loginDTO) {
		
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken(loginDTO.getUser(), loginDTO.getPass());
		
		Authentication authenticate = manager.authenticate(auth);
		String token = this.tokenService.generateToken(authenticate);
		
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
	}
	
	@GetMapping("/whoami")
	public ResponseEntity<User> whoami(@RequestHeader("Authorization") String token) {

		Long userId = tokenService.getUserIdFromToken(token.substring(7));
		User u = userClient.getById(userId);
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	
}
