package com.algafood.core.security.authorizationserver;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

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
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		return http
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	ProviderSettings providerSettings(AlgafoodSecurityProperties properties) {
		return ProviderSettings.builder()
				.issuer(properties.getProviderUrl())
				.build();
	}
	
	@Bean
	RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
		
		RegisteredClient algafoodBackend = RegisteredClient 
				 .withId("1")
				 .clientId("algafood-back")
				 .clientSecret(passwordEncoder.encode("web123"))
				 .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				 .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				 .scope("READ")
				 .tokenSettings(TokenSettings.builder()
						 .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						 .accessTokenTimeToLive(Duration.ofMinutes(30))
						 .build())
				 .build();
		
		RegisteredClient algafoodWeb = RegisteredClient
				.withId("2")
				.clientId("algafood-web")
				.clientSecret(passwordEncoder.encode("web123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.scope("READ")
				.scope("WRITE")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(15))
						.reuseRefreshTokens(false)
						.refreshTokenTimeToLive(Duration.ofDays(1))
						.build())
			    .redirectUri("http://127.0.0.1:8082")
			    .redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html")
			    .clientSettings(ClientSettings.builder()
			    		.requireAuthorizationConsent(true)
			    		.build())
			    .build();
				
		RegisteredClient foodanalytics = RegisteredClient
				.withId("3")
				.clientId("foodanalytics")
				.clientSecret(passwordEncoder.encode("web123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.scope("READ")
				.scope("WRITE")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(30))
						.reuseRefreshTokens(false)
						.refreshTokenTimeToLive(Duration.ofDays(1))
						.build())
			    .redirectUri("http://127.0.0.1:8082")
			    .redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html")
			    .clientSettings(ClientSettings.builder()
			    		.requireAuthorizationConsent(true)
			    		.build())
			    .build();
		
		return new InMemoryRegisteredClientRepository(Arrays.asList(algafoodBackend, algafoodWeb, foodanalytics));
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
}
