package com.algafood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.algafood.client.api.ClientApiException;
import com.algafood.client.api.RestauranteClient;
import com.algafood.client.model.RestauranteModel;
import com.algafood.client.model.input.CidadeInputModel;
import com.algafood.client.model.input.CozinhaInputModel;
import com.algafood.client.model.input.EnderecoInputModel;
import com.algafood.client.model.input.RestauranteInputModel;

public class InclusaoRestauranteMain {
	
	public static void main(String[] args) {
		
		try {

			RestauranteInputModel restauranteInputModel = new RestauranteInputModel();
			
			restauranteInputModel.setNome("");
			restauranteInputModel.setTaxaFrete(new BigDecimal("12"));
			
			CozinhaInputModel cozinha = new CozinhaInputModel();
			//cozinha.setId(1L);
			
			restauranteInputModel.setCozinha(cozinha);
			
			EnderecoInputModel endereco = new EnderecoInputModel();
			endereco.setBairro("Centro");
			endereco.setCep("38400-999");
			endereco.setLogradouro("Rua Afonso Pena");
			endereco.setNumero("1500");
			
			CidadeInputModel cidade = new CidadeInputModel();
			cidade.setId(2L);
			
			endereco.setCidade(cidade);
			
			restauranteInputModel.setEndereco(endereco);
			
			RestTemplate restTemplate = new RestTemplate();
			
			RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://algafood.local:8080");
			
			RestauranteModel restauranteModel = restauranteClient.adicionar(restauranteInputModel);
			
			System.out.println(restauranteModel);
		} catch(ClientApiException e) {
			if(e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().forEach(obj -> System.out.println("- " + obj.getUserMessage()));
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}
}
