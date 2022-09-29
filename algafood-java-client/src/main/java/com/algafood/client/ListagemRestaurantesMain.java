package com.algafood.client;

import org.springframework.web.client.RestTemplate;

import com.algafood.client.api.ClientApiException;
import com.algafood.client.api.RestauranteClient;

public class ListagemRestaurantesMain {

	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			RestauranteClient restauranteClient = new RestauranteClient(restTemplate, "http://algafood.local:8080");
			
			restauranteClient.listar().forEach(System.out::println);
		} catch (ClientApiException e) {
			if(e.getProblem() != null) {
				System.out.println(e.getProblem());
				
				System.out.println(e.getProblem().getUserMessage());
			} else { 
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}
}
