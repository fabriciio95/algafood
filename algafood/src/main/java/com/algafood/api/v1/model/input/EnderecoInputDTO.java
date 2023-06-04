package com.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDTO {

	@Schema(example = "06183-938")
	@NotBlank
	private String cep;
	
	@Schema(example = "Av Ipiranga")
	@NotBlank
	private String logradouro;
	
	@Schema(example = "100")
	@NotBlank
	private String numero;
	
	@Schema(example = "Apto 99")
	private String complemento;
	
	@Schema(example = "Jd das Cruzes")
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInputDTO cidade;
}
