package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.FormaPagamentoDTOAssembler;
import com.algafood.api.assembler.FormaPagamentoDTODisassembler;
import com.algafood.api.model.FormaPagamentoDTO;
import com.algafood.api.model.input.FormaPagamentoInputDTO;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.repository.FormaPagamentoRepository;
import com.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@Autowired
	private FormaPagamentoDTODisassembler formaPagamentoDTODisassembler;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		return formaPagamentoDTOAssembler.toListDTO(formaPagamentoRepository.findAll());
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
		return formaPagamentoDTOAssembler.toDTO(cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInputDTO) {
		
		FormaPagamento formaPagamento = formaPagamentoDTODisassembler.toDomainObject(formaPagamentoInputDTO);
		
		return formaPagamentoDTOAssembler.toDTO(cadastroFormaPagamentoService.salvar(formaPagamento));
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInputDTO) {
		
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		
		formaPagamentoDTODisassembler.copyToDomainObject(formaPagamentoInputDTO, formaPagamentoAtual);
		
		return formaPagamentoDTOAssembler.toDTO(cadastroFormaPagamentoService.salvar(formaPagamentoAtual));
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{formaPagamentoId}")
	public void excluir(@PathVariable Long formaPagamentoId) {
		 cadastroFormaPagamentoService.excluir(formaPagamentoId);
	}
}
