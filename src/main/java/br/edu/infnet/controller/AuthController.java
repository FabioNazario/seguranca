package br.edu.infnet.controller;

import java.util.HashSet;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.client.UserClient;
import br.edu.infnet.model.LoginDTO;
import br.edu.infnet.model.TokenDTO;
import br.edu.infnet.model.User;
import br.edu.infnet.model.UserDTO;
import br.edu.infnet.service.TokenService;

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
	public ResponseEntity<UserDTO> whoami(@RequestHeader("Authorization") String token) {
		
		try {
			token = StringUtils.removeStart(token, "Bearer").trim();
			Long userId = tokenService.getUserIdFromToken(token);
	        UserDTO u =  userClient.getByIdWhoami(userId);
			return new ResponseEntity<UserDTO>(u,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	
}
