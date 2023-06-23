package com.algafood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class ResourceServerConfig {

	@Bean
	SecurityFilterChain resourcerServerFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(customizer -> customizer.loginPage("/login"))      
		          .authorizeRequests()
				  .antMatchers("/oauth2/**").authenticated()
				  .and()
				  .csrf().disable()
				  .cors()
				  .and()
				  .oauth2ResourceServer()
				  .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
				
		 return http.build();
	}
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			List<String> authorities = jwt.getClaimAsStringList("authorities");
			
			if(authorities == null) {
				return Collections.emptyList();
			}
			
			JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
			
			Collection<GrantedAuthority> grantedAuthorities = authoritiesConverter.convert(jwt);
			
			grantedAuthorities.addAll(authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.toList());
			
			return grantedAuthorities;
		});
		
		return converter;
	}
}
