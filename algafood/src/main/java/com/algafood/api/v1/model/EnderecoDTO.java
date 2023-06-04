package com.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

	@Schema(example = "06849-384")
	private String cep;
	
	@Schema(example = "Rua dos Imigrantes")
	private String logradouro;
	
	@Schema(example = "1390")
	private String numero;
	
	@Schema(example = "Casa")
	private String complemento;
	
	@Schema(example = "Jardim Olímpia")
	private String bairro;
	
	private CidadeResumoDTO cidade;
}
