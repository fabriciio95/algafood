package com.algafood.api.v1.model.input;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdInputDTO {

	@Schema(example = "1")
	@NotNull
	private Long id;
}
