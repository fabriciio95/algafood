package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.algafood.api.assembler.EstadoDTOAssembler;
import com.algafood.api.assembler.EstadoInputDTODisassembler;
import com.algafood.api.model.EstadoDTO;
import com.algafood.api.model.input.EstadoInputDTO;
import com.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;
import com.algafood.domain.service.CadastroEstadoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class EstadoController implements EstadoControllerOpenApi {

	private EstadoRepository estadoRepository;

	private CadastroEstadoService cadastroEstado;
	
	private EstadoDTOAssembler estadoDTOAssembler;
	
	private EstadoInputDTODisassembler estadoInputDTODisassembler;

	@GetMapping
	public List<EstadoDTO> listar() {
		return estadoDTOAssembler.toListDTO(estadoRepository.findAll());
	}

	@GetMapping("/{estadoId}")
	public EstadoDTO buscar(@PathVariable Long estadoId) {
		return estadoDTOAssembler.toDTO(cadastroEstado.buscarOuFalhar(estadoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoDTO adicionar(@RequestBody @Valid EstadoInputDTO estadoInputDTO) {
		
		Estado estado = estadoInputDTODisassembler.toDomainObject(estadoInputDTO);
		
		return estadoDTOAssembler.toDTO(estadoRepository.save(estado));
	}

	@PutMapping("/{estadoId}")
	public EstadoDTO atualizar(@RequestBody @Valid EstadoInputDTO estadoInputDTO, @PathVariable Long estadoId) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		
		estadoInputDTODisassembler.copyToDomainObject(estadoInputDTO, estadoAtual);

		return estadoDTOAssembler.toDTO(cadastroEstado.salvar(estadoAtual));

	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}
}
