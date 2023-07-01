package com.algafood.api.v1.model.input;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import com.algafood.domain.model.Restaurante;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RestauranteInputDTO {

	@Schema(example = "Brasileirissimo")
	@NotBlank
	private String nome;
	
	@Schema(example = "9.99")
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInputDTO cozinha;
	
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
