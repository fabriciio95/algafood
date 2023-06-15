package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInputDTO {

	@Schema(example = "123456")
	@NotBlank
	private String senhaAtual;
	
	@Schema(example = "12345678")
	@NotBlank
	private String novaSenha;
	
}
