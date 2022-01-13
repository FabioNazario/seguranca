package br.edu.infnet.service;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.infnet.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private String secretKey = "2dfb811abb93f5644eaad865c4e466020a787d17d74b4f3c6ad2a07f8a7c796a";// TODO colocar no servidor de configuracao
	
	public String generateToken(Authentication auth) {
		User user = (User) auth.getPrincipal();
		Date now = new Date();
		Date exp = new Date((now.getTime()) + (6000*60*60));
		
		return Jwts.builder().setIssuer("VARIAVEL_AQUI?")
		.setSubject(user.getId().toString())
		.setIssuedAt(new Date())
		.setExpiration(exp)
		.claim("user", user.getUsername())
		.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		
	}
	
	public Long getUserIdFromToken(String token) {
		Claims body = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		
		return Long.valueOf(body.getSubject());
	}	
	
	public String getUserNameFromToken(String token) {

		return (String) Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.get("user");
		
	}
	
	public Boolean isValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
