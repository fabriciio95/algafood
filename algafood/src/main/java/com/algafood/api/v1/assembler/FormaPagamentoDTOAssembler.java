package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.FormaPagamentoController;
import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public FormaPagamentoDTOAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoDTO.class);
	}
	
	@Override
	public FormaPagamentoDTO toModel(FormaPagamento formaPagamento) {
		FormaPagamentoDTO formaPagamentoDTO = createModelWithId(formaPagamento.getId(), formaPagamento);
		
		modelMapper.map(formaPagamento, formaPagamentoDTO);
		
		formaPagamentoDTO.add(algaLinks.linkToFormasPagamento("formasPagamento"));
		
		return formaPagamentoDTO;
	}
	
	@Override
	public CollectionModel<FormaPagamentoDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToFormasPagamento());
	}
}
