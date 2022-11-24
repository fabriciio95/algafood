package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.algafood.api.v1.model.input.CozinhaIdInputDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteInputModel")
@Setter
@Getter
public class RestauranteInputModelOpenApi {

	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;
	@ApiModelProperty(example = "12.00")
	private BigDecimal taxaFrete;
	private CozinhaIdInputDTO cozinha;
	private EnderecoInputModelOpenApi endereco;
}
