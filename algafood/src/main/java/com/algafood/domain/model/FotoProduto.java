package com.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produto_id")
	private Long id;
	
	private String nomeArquivo;
	
	private String descricao;
	
	private String contentType;
	
	private Long tamanho;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
	
	public Long getRestauranteId() {
		if(getProduto() != null) {
			return getProduto().getRestaurante().getId();
		}
		
		return null;
	}
}
