package com.algafood.core.springdoc;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

	//@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Algafood API")
						.version("v1")
						.description("REST API do Algafood")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.com")
						)
				 ).externalDocs(new ExternalDocumentation()
						 .description("AlgaWorks")
						 .url("https://algaworks.com")
				 );
	}
	
	@Bean
	GroupedOpenApi groupedOpenApi() {
		return GroupedOpenApi.builder()
				         .group("Algafood  API")
				         .pathsToMatch("/v1/**")
				         .addOpenApiCustomiser(openApi -> {
				        	 openApi.info(new Info()
										.title("Algafood API")
										.version("v1")
										.description("REST API do Algafood")
										.license(new License()
												.name("Apache 2.0")
												.url("http://springdoc.com")
										)
								 ).externalDocs(new ExternalDocumentation()
										 .description("AlgaWorks")
										 .url("https://algaworks.com")
								 );
				         }).build();
	}
	
	@Bean
	GroupedOpenApi groupedOpenApi2() {
		return GroupedOpenApi.builder()
				         .group("Algafood  API v2")
				         .pathsToMatch("/v2/**")
				         .addOpenApiCustomiser(openApi -> {
				        	 openApi.info(new Info()
										.title("Algafood API")
										.version("v2")
										.description("REST API do Algafood")
										.license(new License()
												.name("Apache 2.0")
												.url("http://springdoc.com")
										)
								 ).externalDocs(new ExternalDocumentation()
										 .description("AlgaWorks")
										 .url("https://algaworks.com")
								 );
				         }).build();
	}
}
