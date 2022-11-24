package com.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

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
	
	private CidadeResumoDTO cidade;
}
