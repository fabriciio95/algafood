package com.algafood.core.security.authorizationserver;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.UsuarioRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception{
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
				new OAuth2AuthorizationServerConfigurer();
		
	    authorizationServerConfigurer
	    		.authorizationEndpoint(customizer -> customizer.consentPage("/oauth2/consent"));
		
		RequestMatcher endpointsMatcher = authorizationServerConfigurer
				.getEndpointsMatcher();

		http.securityMatcher(endpointsMatcher)
			.authorizeHttpRequests(authorize -> {
				authorize.anyRequest().authenticated();
			})
			.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
			.formLogin(Customizer.withDefaults())
			   .exceptionHandling(exceptions -> {
				   exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
			   })
			.apply(authorizationServerConfigurer);
		return http.build();
	}
	
	@Bean
	AuthorizationServerSettings providerSettings(AlgafoodSecurityProperties properties) {
		return AuthorizationServerSettings.builder()
				.issuer(properties.getProviderUrl())
				.build();
	}
	
	@Bean
	RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder, JdbcOperations jdbcOperations) {
		return new JdbcRegisteredClientRepository(jdbcOperations);
	}
	
	@Bean
	OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations,
			RegisteredClientRepository registeredClientRepository) {
		
		return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
	}
	
	@Bean
	JWKSource<SecurityContext> jwtSource(JwtKeyStoreProperties properties) throws Exception {
		var keyStorePass = properties.getPassword().toCharArray();
		
		var keyPairAlias = properties.getKeypairAlias();
		
		Resource jksLocation = properties.getJksLocation();
		
		InputStream inputStream = jksLocation.getInputStream();
		
		KeyStore keyStore = KeyStore.getInstance("JKS");
		
		keyStore.load(inputStream, keyStorePass);
		
		RSAKey rsaKey = RSAKey.load(keyStore, keyPairAlias, keyStorePass);
		
		return new ImmutableJWKSet<>(new JWKSet(rsaKey));
		
	}
	
	@Bean
	OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(UsuarioRepository usuarioRepository) {
		return context -> {
			Authentication authentication = context.getPrincipal();
			if(authentication.getPrincipal() instanceof User) {
				User user = (User) authentication.getPrincipal();
				
				Usuario usuario = usuarioRepository.findByEmail(user.getUsername()).orElseThrow();
				
				Set<String> authorities = user.getAuthorities()
						.stream()
						.map(a-> a.getAuthority())
						.collect(Collectors.toSet());
				
				context.getClaims().claim("usuario_id", usuario.getId().toString());
				context.getClaims().claim("authorities", authorities);
				
			}
		};
	}
	
	@Bean
	OAuth2AuthorizationConsentService consentService(JdbcOperations jdbcOperations, 
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, registeredClientRepository);
	}
	
	@Bean
	OAuth2AuthorizationQueryService auth2AuthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationQueryService(jdbcOperations, registeredClientRepository);
	}
}
