package com.algafood.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algafood.api.assembler.FormaPagamentoDTOAssembler;
import com.algafood.api.assembler.FormaPagamentoDTODisassembler;
import com.algafood.api.model.FormaPagamentoDTO;
import com.algafood.api.model.input.FormaPagamentoInputDTO;
import com.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.repository.FormaPagamentoRepository;
import com.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
	
	@Autowired
	private FormaPagamentoDTODisassembler formaPagamentoDTODisassembler;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@GetMapping
	public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();
		
		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamentoDTO> formasPagamento = formaPagamentoDTOAssembler.toListDTO(formaPagamentoRepository.findAll());
		
		return ResponseEntity.ok()
							 .cacheControl(CacheControl.maxAge(10L, TimeUnit.SECONDS).cachePublic())
							 .eTag(eTag)
							 .body(formasPagamento);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataAtualizacaoById(formaPagamentoId);
		
		if(dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if(request.checkNotModified(eTag)) {
			return null;
		}
		
		FormaPagamentoDTO formaPagamentoDTO = formaPagamentoDTOAssembler.toDTO(cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId));
	
		return ResponseEntity.ok()
				 			  .cacheControl(CacheControl.maxAge(10L, TimeUnit.SECONDS))
				 			  .body(formaPagamentoDTO);
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
