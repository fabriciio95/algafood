package com.algafood.api.v1.controller;

import jakarta.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.EstadoDTOAssembler;
import com.algafood.api.v1.assembler.EstadoInputDTODisassembler;
import com.algafood.api.v1.model.EstadoDTO;
import com.algafood.api.v1.model.input.EstadoInputDTO;
import com.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.service.CadastroEstadoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstadoController implements EstadoControllerOpenApi {

	private EstadoRepository estadoRepository;

	private CadastroEstadoService cadastroEstado;
	
	private EstadoDTOAssembler estadoDTOAssembler;
	
	private EstadoInputDTODisassembler estadoInputDTODisassembler;

	@CheckSecurity.Estados.PodeConsultar
	@GetMapping
	public CollectionModel<EstadoDTO> listar() {
		return estadoDTOAssembler.toCollectionModel(estadoRepository.findAll());
	}

	@CheckSecurity.Estados.PodeConsultar
	@GetMapping("/{estadoId}")
	public EstadoDTO buscar(@PathVariable Long estadoId) {
		return estadoDTOAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId));
	}

	@CheckSecurity.Estados.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid EstadoInputDTO estadoInputDTO) {
		
		Estado estado = estadoInputDTODisassembler.toDomainObject(estadoInputDTO);
		
		return estadoDTOAssembler.toModel(estadoRepository.save(estado));
	}

	@CheckSecurity.Estados.PodeEditar
	@PutMapping("/{estadoId}")
	public EstadoDTO atualizar(@RequestBody @Valid EstadoInputDTO estadoInputDTO, @PathVariable Long estadoId) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		
		estadoInputDTODisassembler.copyToDomainObject(estadoInputDTO, estadoAtual);

		return estadoDTOAssembler.toModel(cadastroEstado.salvar(estadoAtual));

	}

	@CheckSecurity.Estados.PodeEditar
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}
}
