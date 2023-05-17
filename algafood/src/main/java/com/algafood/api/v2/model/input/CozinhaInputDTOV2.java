package com.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDTOV2 {

	@NotBlank
	private String nomeCozinha;
}
