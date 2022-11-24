package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.model.input.FormaPagamentoInputDTO;
import com.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTODisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInputDTO formaPagamentoInputDTO) {
		return modelMapper.map(formaPagamentoInputDTO, FormaPagamento.class);
	}
	
	public void copyToDomainObject(FormaPagamentoInputDTO dto, FormaPagamento domainObject) {
		modelMapper.map(dto, domainObject);
	}
}
