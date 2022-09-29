package com.algafood.client;

import org.springframework.web.client.RestTemplate;

import com.algafood.client.api.RestauranteClient;

public class ListagemRestaurantesMain {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		
		RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://algafood.local:8080");
		
		restauranteClient.listar().forEach(System.out::println);
	}
}
