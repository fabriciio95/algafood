package com.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDTO {

	@ApiModelProperty(example = "38400-222", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Rua Natal", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "200", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Apartamento 113 Bloco B")
	private String complemento;
	
	@ApiModelProperty(example = "Centro", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInputDTO cidade;
}
