package com.example.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	//Configuración por default de Spring Security
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(
//				auth -> auth.anyRequest().authenticated())
//		.formLogin(Customizer.withDefaults())
//		.httpBasic(Customizer.withDefaults());
//
//		return http.build();
//	}
	
	/***
	 * Configuración para Spring security con los EndPoints que se requieren atenticar
	 * y con anyrequest.permitAll se permite acceder a los demas endpoint sin atenticar
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/loans", "/balance", "/accounts", "/cards").permitAll()
				.anyRequest().authenticated())
		.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
