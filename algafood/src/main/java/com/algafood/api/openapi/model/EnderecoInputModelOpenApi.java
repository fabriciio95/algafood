package com.algafood.api.openapi.model;

import com.algafood.api.model.input.CidadeIdInputDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("EnderecoInputModel")
@Getter
@Setter
public class EnderecoInputModelOpenApi {

	@ApiModelProperty(example = "38400-222")
	private String cep;
	
	@ApiModelProperty(example = "Rua Natal")
	private String logradouro;
	
	@ApiModelProperty(example = "200")
	private String numero;
	
	@ApiModelProperty(example = "Apartamento 113 Bloco B")
	private String complemento;
	
	@ApiModelProperty(example = "Centro")
	private String bairro;
	
	private CidadeIdInputDTO cidade;
}
