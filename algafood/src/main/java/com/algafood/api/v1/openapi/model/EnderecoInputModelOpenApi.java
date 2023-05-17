package com.algafood.api.v1.openapi.model;

import com.algafood.api.v1.model.input.CidadeIdInputDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputModelOpenApi {

	private String cep;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private CidadeIdInputDTO cidade;
}
