package com.algafood.client.model.input;

import lombok.Data;

@Data
public class EnderecoInputModel {

	private String cep;
	
	private String logradouro;
	
	private String numero; 
	
	private String complemento;
	
	private String bairro;
	
	private CidadeInputModel cidade;
}
