package com.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.util.DatabaseCleaner;
import com.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaApiIT {
	
	private static final int COZINHA_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;
	
	private int qtdCozinhasCadastradas;
	
	private String jsonCorettoCozinhaChinesa;
	
	private Cozinha cozinhaAmericana;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	
	@Before
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		jsonCorettoCozinhaChinesa =  ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");
		
		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveriaRetornarStatus200_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
	   .when()
	   		.get()
	   .then()
	   		.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveriaRetornarTodasCozinhasCadastradas_QuandoConsultarCozinhas() {
		
		given()
			.accept(ContentType.JSON)
	   .when()
	   		.get()
	   .then()
	   		.body("", hasSize(qtdCozinhasCadastradas))
	   		.body("nome", hasItems("Americana", "Tailandesa"));
	}
	
	@Test
	public void deveriaRetornarStatus201_QuandoCadastrarCozinha() {
		
		given()
			.body(jsonCorettoCozinhaChinesa)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	
	public void deveriaRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));
	}
	
	public void deveriaRetornarStatus404_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		Cozinha cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);
		
		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaAmericana = cozinhaRepository.save(cozinhaAmericana);
		
		qtdCozinhasCadastradas = (int) cozinhaRepository.count();
	}
}
