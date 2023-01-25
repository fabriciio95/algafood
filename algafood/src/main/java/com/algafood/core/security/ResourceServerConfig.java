package com.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.cors()
			.and()
			.oauth2ResourceServer().jwt();
	}
	
//	@Bean
//	public JwtDecoder jwtDecoder() {
//		var secretKey = new SecretKeySpec("3290402342kl34m23k4lm2fdf09o3k2mllrmcsdfpo".getBytes(), "HmacSHA256");
//		
//		return NimbusJwtDecoder
//				.withSecretKey(secretKey)
//				.build();
//	}
}
