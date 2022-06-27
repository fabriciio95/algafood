package com.algafood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.model.FormaPagamentoDTO;
import com.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
	}
	
	public List<FormaPagamentoDTO> toListDTO(Collection<FormaPagamento> formasPagamentos) {
		return formasPagamentos.stream().map(this::toDTO).collect(Collectors.toList());
	}
}
