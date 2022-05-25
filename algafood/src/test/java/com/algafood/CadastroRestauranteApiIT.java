package com.algafood;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteApiIT {

	private static final int RESTAURANTE_ID_INEXISTENTE = 100;
	
	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";

	}
	
	@Test
	@Sql(scripts =  "/sql/dadosiniciais.sql")
	public void deveriaRetornar6cozinhas_QuandoConsultarTodas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(6));
	}
	
	@Test
	@Sql(scripts =  "/sql/dadosiniciais.sql")
	public void deveriaRetornarRestauranteThaiGourmet_QuandoConsultarPorId1() {
		given()
			.pathParam("restauranteId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("Thai Gourmet"));
			
	}
	
	@Test
	@Sql(scripts =  "/sql/dadosiniciais.sql")
	public void deveriaRetornarStatus404_QuandoConsultarPorIdInexistente() {
		given()
			.pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
			
	}
	
	@Test
	public void deveriaCadastrarRestaurante_QuandoInformarDadosCorretos() {
		String jsonRestaurante = ResourceUtils.getContentFromResource("/json/correto/restaurante-chocobiz.json");
		given()
			.body(jsonRestaurante)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("id", Matchers.notNullValue(Long.class));
	}
	
	@Test
	public void naoDeveriaCadastrarRestaurante_QuandoNaoInformaFrete() {
		String jsonRestaurante = ResourceUtils.getContentFromResource("/json/errado/restaurante-sem-frete.json");
		given()
			.body(jsonRestaurante)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void naoDeveriaCadastrarRestaurante_QuandoNaoInformaCozinha() {
		String jsonRestaurante = ResourceUtils.getContentFromResource("/json/errado/restaurante-sem-cozinha.json");
		given()
			.body(jsonRestaurante)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void naoDeveriaCadastrarRestaurante_QuandoInformaCozinhaNaoCadastrada() {
		String jsonRestaurante = ResourceUtils.getContentFromResource("/json/errado/restaurante-cozinha-nao-cadastrada.json");
		given()
			.body(jsonRestaurante)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	@Sql(scripts =  "/sql/dadosiniciais.sql")
	public void deveriaAtualizarRestauranteThaiGourmet_QuandoInformaDadosCorretos() {
		
		String jsonRestaurante = ResourceUtils.getContentFromResource("/json/correto/restaurante-chocobiz.json");
		
		given()
			.pathParam("restauranteId", 1)
			.body(jsonRestaurante)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("Chocobiz"));
	}
	
	@Test
	@Sql(scripts =  "/sql/dadosiniciais.sql")
	public void deveriaAtualizarApenasNomeDoRestauranteThaiGourmet_QuandoInformaDadosCorretos() {
		
		given()
			.pathParam("restauranteId", 1)
			.body("{ \"nome\" : \"Chocobiz\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.patch("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("Chocobiz"))
			.body("cozinha.id", Matchers.is(1));
	}
	
	

}
