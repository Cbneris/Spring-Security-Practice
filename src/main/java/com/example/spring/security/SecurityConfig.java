package com.example.spring.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
				//.requestMatchers("/loans", "/balance", "/accounts", "/cards").authenticated()
				.requestMatchers("/loans", "/balance").hasRole("USER")
				.requestMatchers("/cards", "/accounts").hasRole("ADMIN")
				.anyRequest().permitAll())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		
		http.cors(cors -> corsConfiguration());

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfiguration() {
		var config = new CorsConfiguration();

		config.setAllowedOrigins(List.of("http://localhost:4200"));
//		config.setAllowedOrigins(List.of("*"));
		
		config.setAllowedMethods(List.of("get", "post", "put", "delete"));
//		config.setAllowedMethods(List.of("*"));
		
		config.setAllowedHeaders(List.of("*"));
		
		var source = new UrlBasedCorsConfigurationSource(); 
		
//		/**: Todo
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}

//    @Bean
//    PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
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

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
