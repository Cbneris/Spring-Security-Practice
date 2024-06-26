package com.example.spring.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyFilter extends OncePerRequestFilter{
	
	private static final String API_KEY 		= "myKey";
	private static final String API_KEY_HEADER 	= "api_key";

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		try {

			final var apiKeyOpt = Optional.of(request.getHeader(API_KEY_HEADER));
			final var apiKey 	= apiKeyOpt.orElseThrow( () -> new BadCredentialsException("No Header Api Key") );
			
			if(!apiKey.equals(API_KEY)) {
				new BadCredentialsException("Invalid Api Key !");
			}
			
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid Api Key -_-");
		}
		
		filterChain.doFilter(request, response);
		
	}

}