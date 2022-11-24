package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDTO {

	@ApiModelProperty(example = "Italiana", required = true)
	@NotBlank
	private String nome;
}
