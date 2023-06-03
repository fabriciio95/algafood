package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDTO {

	@Schema(example = "Brasileira")
	@NotBlank
	private String nome;
}
