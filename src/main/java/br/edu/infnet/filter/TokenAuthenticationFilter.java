package br.edu.infnet.filter;

import java.io.IOException;
import java.security.Security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.infnet.model.User;
import br.edu.infnet.repository.UserRepository;
import br.edu.infnet.service.TokenService;

public class TokenAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserRepository userRepository;
	
	public TokenAuthenticationFilter(ApplicationContext ctx) {
		
		this.tokenService = ctx.getBean(TokenService.class);
		this.userRepository = ctx.getBean(UserRepository.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String token = getToken(request);
		if(this.tokenService.isValid(token)) {

			Long userId = this.tokenService.getUserIdFromToken(token);
			User user = userRepository.findById(userId).get();
			
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(user, null, user.getPerfis());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			
		}
		filterChain.doFilter(request, response);
		
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7);
		
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		String path = request.getRequestURI();
		return "/auth".equals(path);
	}


}
