package com.algafood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.EstadoDTO;
import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.model.PedidoResumoDTO;
import com.algafood.api.v1.model.PermissaoDTO;
import com.algafood.api.v1.model.ProdutoDTO;
import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.api.v1.openapi.model.CidadesModelOpenApi;
import com.algafood.api.v1.openapi.model.CozinhasDTOOpenApi;
import com.algafood.api.v1.openapi.model.EnderecoInputModelOpenApi;
import com.algafood.api.v1.openapi.model.EstadosModelOpenApi;
import com.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import com.algafood.api.v1.openapi.model.GruposModelOpenApi;
import com.algafood.api.v1.openapi.model.LinksModelOpenApi;
import com.algafood.api.v1.openapi.model.PageableModelOpenApi;
import com.algafood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import com.algafood.api.v1.openapi.model.PermissoesModelOpenApi;
import com.algafood.api.v1.openapi.model.ProdutosModelOpenApi;
import com.algafood.api.v1.openapi.model.RestauranteInputModelOpenApi;
import com.algafood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;
import com.algafood.api.v1.openapi.model.UsuariosModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocketV1() {
		
		var typeResolver = new TypeResolver();
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algafood.api"))
					.paths(PathSelectors.ant("/v1/**"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.PATCH, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class),
						typeResolver.resolve(RestauranteInputModelOpenApi.class),
						typeResolver.resolve(EnderecoInputModelOpenApi.class))
				.ignoredParameterTypes(ServletWebRequest.class,
									   HttpServletRequest.class,
									   URL.class,
									   URI.class,
									   URLStreamHandler.class,
									   Resource.class,
									   File.class,
									   InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(
						AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CozinhaDTO.class), CozinhasDTOOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, PedidoResumoDTO.class), PedidosResumoModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CidadeDTO.class), CidadesModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, EstadoDTO.class), EstadosModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, FormaPagamentoDTO.class), FormasPagamentoModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, GrupoDTO.class), GruposModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PermissaoDTO.class), PermissoesModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, ProdutoDTO.class), ProdutosModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, RestauranteBasicoDTO.class), RestaurantesBasicoModelOpenApi.class),
						AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, UsuarioDTO.class), UsuariosModelOpenApi.class))
				.apiInfo(apiInfoV1())
				.tags(new Tag("Cidades", "Gerencia as cidades"),
					  new Tag("Grupos", "Gerencia os grupos"),
					  new Tag("Cozinhas", "Gerencia as cozinhas"),
					  new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"),
					  new Tag("Pedidos", "Gerencia os pedidos"),
					  new Tag("Restaurantes", "Gerencia os restaurantes"),
					  new Tag("Estados", "Gerencia os estados"),
					  new Tag("Produtos", "Gerencia os produtos de restaurantes"),
					  new Tag("Usuários", "Gerencia os usuários"),
					  new Tag("Estatísticas", "Estatísticas da AlgaFood"),
					  new Tag("Permissões", "Gerencia as permissões"));
	}
	
	@Bean
	public Docket apiDocketV2() {
		
		var typeResolver = new TypeResolver();
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V2")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algafood.api"))
					.paths(PathSelectors.ant("/v2/**"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.PATCH, globalPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class),
						typeResolver.resolve(RestauranteInputModelOpenApi.class),
						typeResolver.resolve(EnderecoInputModelOpenApi.class))
				.ignoredParameterTypes(ServletWebRequest.class,
									   HttpServletRequest.class,
									   URL.class,
									   URI.class,
									   URLStreamHandler.class,
									   Resource.class,
									   File.class,
									   InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.apiInfo(apiInfoV2());
	}
	
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
					new ResponseMessageBuilder()
							.code(HttpStatus.BAD_REQUEST.value())
							.message("Requisição inválida (erro do cliente)")
							.responseModel(new ModelRef("Problema"))
							.build(),
							
					new ResponseMessageBuilder()
							.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
							.message("Erro interno no servidor")
							.responseModel(new ModelRef("Problema"))
							.build()
				);
	}

	private List<ResponseMessage> globalPutResponseMessages() {
		return Arrays.asList(
					new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Requisição inválida (erro do cliente)")
						.responseModel(new ModelRef("Problema"))
						.build(),
						
					new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno no servidor")
						.responseModel(new ModelRef("Problema"))
						.build(),
						
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
						.build(),
						
					new ResponseMessageBuilder()
						.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Requisição recusada porque o corpo está em um formato não suportado")
						.responseModel(new ModelRef("Problema"))
						.build()
				);
	}

	private List<ResponseMessage> globalPostResponseMessages() {
		return Arrays.asList(
					new ResponseMessageBuilder()
						.code(HttpStatus.BAD_REQUEST.value())
						.message("Requisição inválida (erro do cliente)")
						.responseModel(new ModelRef("Problema"))
						.build(),
						
					new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno no servidor")
						.responseModel(new ModelRef("Problema"))
						.build(),
					
					new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
						.build(),
						
					new ResponseMessageBuilder()
						.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Requisição recusada porque o corpo está em um formato não suportado")
						.responseModel(new ModelRef("Problema"))
						.build()
				);
	}

	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
					new ResponseMessageBuilder()
							.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
							.message("Erro interno do servidor")
							.responseModel(new ModelRef("Problema"))
							.build(),
							
					new ResponseMessageBuilder()
							.code(HttpStatus.NOT_ACCEPTABLE.value())
							.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
							.build()
							
				);
	}
	
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
					.title("Algafood API")
					.description("API aberta para clientes e restaurantes")
					.version("1")
					.contact(new Contact("Fabricio", "https://github.com/fabriciio95", "fabriciousiqueira@gmail.com"))
				.build();
	}
	
	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
					.title("Algafood API")
					.description("API aberta para clientes e restaurantes")
					.version("2")
					.contact(new Contact("Fabricio", "https://github.com/fabriciio95", "fabriciousiqueira@gmail.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
