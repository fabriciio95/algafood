package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInputDTO {

	@ApiModelProperty(example = "12345678", required = true)
	@NotBlank
	private String senhaAtual;
	
	@ApiModelProperty(example = "12345678", required = true)
	@NotBlank
	private String novaSenha;
	
}
