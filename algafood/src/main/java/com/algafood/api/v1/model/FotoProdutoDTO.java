package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.FotoProdutoOpenApiDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "fotos")
@Getter
@Setter
@Schema(implementation = FotoProdutoOpenApiDTO.class)
public class FotoProdutoDTO extends RepresentationModel<FotoProdutoDTO> {
	
	private String nomeArquivo;

	private String descricao;

	private String contentType;

	private Long tamanho;
}
