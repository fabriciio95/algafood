package com.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

	@ApiModelProperty(example = "João da Silva", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "joao@algafood.com", required = true)
	@Email
	@NotBlank
	private String email;
	
}
