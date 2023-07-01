package com.algafood.api.v1.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

	@Schema(example = "João da Silva")
	@NotBlank
	private String nome;
	
	@Schema(example = "joao.silva@algafood.com")
	@Email
	@NotBlank
	private String email;
	
}
