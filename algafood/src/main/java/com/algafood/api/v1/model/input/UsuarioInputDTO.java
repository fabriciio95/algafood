package com.algafood.api.v1.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

	@NotBlank
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
}
