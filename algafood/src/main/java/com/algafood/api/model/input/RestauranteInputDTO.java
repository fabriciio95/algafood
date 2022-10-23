package com.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.algafood.domain.model.Restaurante;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RestauranteInputDTO {

	@ApiModelProperty(example = "Thai Gourmet")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "12.00")
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(required = true)
	@Valid
	@NotNull
	private CozinhaIdInputDTO cozinha;
	
	@ApiModelProperty(required = true)
	@Valid
	@NotNull
	private EnderecoInputDTO endereco;
	
	public RestauranteInputDTO(Restaurante restaurante) {
		   this.nome = restaurante.getNome();
		   this.taxaFrete = restaurante.getTaxaFrete();
		   this.cozinha = new CozinhaIdInputDTO();
		   this.cozinha.setId(restaurante.getCozinha().getId());
		   this.endereco = new EnderecoInputDTO();
		   
		   if(restaurante.getEndereco() != null) {
			   this.endereco.setBairro(restaurante.getEndereco().getBairro());
			   this.endereco.setCep(restaurante.getEndereco().getCep());
			   this.endereco.setComplemento(restaurante.getEndereco().getComplemento());
			   this.endereco.setLogradouro(restaurante.getEndereco().getLogradouro());
			   this.endereco.setNumero(restaurante.getEndereco().getNumero());
			   this.endereco.setCidade(new CidadeIdInputDTO());
			   this.endereco.getCidade().setId(restaurante.getEndereco().getCidade().getId());
		   }
	}
	
	
	
}
