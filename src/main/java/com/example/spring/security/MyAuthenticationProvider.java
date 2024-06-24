package com.example.spring.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.spring.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider{
	
	private CustomerRepository customerRepository;
	private PasswordEncoder pwdEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final var username = authentication.getName();
		final var pwd = authentication.getCredentials().toString();
		
		final var customerFromDB = this.customerRepository.findByEmail(username);
		final var customer = customerFromDB.orElseThrow(() -> new BadCredentialsException("Invalid Credentialss"));
		final var customerPwd = customer.getPassword();
		
		if(pwdEncoder.matches(pwd, customerPwd)) {
			final var authorities = Collections.singletonList(new SimpleGrantedAuthority(customer.getRole()));
			
			return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
		}else {
			throw new BadCredentialsException("Invalid Credentialss");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
