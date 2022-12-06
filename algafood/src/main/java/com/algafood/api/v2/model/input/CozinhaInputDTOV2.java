package com.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDTOV2 {

	@ApiModelProperty(example = "Italiana", required = true)
	@NotBlank
	private String nomeCozinha;
}
