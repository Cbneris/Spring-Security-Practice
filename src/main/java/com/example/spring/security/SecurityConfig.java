package com.example.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/loans", "/balance", "/accounts", "/cards").authenticated()
				.anyRequest().permitAll())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());

		return http.build();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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
	
//	@Bean
//	InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//
//		UserDetails admin = User.withUsername("admin")
//				.password("password")
//				.authorities("ADMIN")
//				.build();
//		
//		UserDetails user = User.withUsername("user")
//				.password("password")
//				.authorities("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin, user);
//	}
	
//	@Bean
//	UserDetailsService userDetailsService(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
}
