package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.v1.openapi.model.FormaPagamentoModelOpenApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
@Schema(name = "FormaPagamentoDTO", implementation = FormaPagamentoModelOpenApi.class)
public class FormaPagamentoDTO extends RepresentationModel<FormaPagamentoDTO> {

	private Long id;
	
	private String descricao;
}
