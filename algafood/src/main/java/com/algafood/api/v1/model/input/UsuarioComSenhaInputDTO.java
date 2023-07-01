package com.algafood.api.v1.model.input;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInputDTO extends UsuarioInputDTO {

	@Schema(example = "123456")
	@NotBlank
	private String senha;
}
