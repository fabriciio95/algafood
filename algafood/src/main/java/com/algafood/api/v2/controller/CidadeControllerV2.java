package com.algafood.api.v2.controller;

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

import com.algafood.api.utils.ResourceUriHelper;
import com.algafood.api.v2.assembler.CidadeDTOAssemblerV2;
import com.algafood.api.v2.assembler.CidadeInputDTODisassemblerV2;
import com.algafood.api.v2.model.CidadeDTOV2;
import com.algafood.api.v2.model.input.CidadeInputDTOV2;
import com.algafood.api.v2.openapi.controller.CidadeControllerOpenApiV2;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CidadeControllerV2  implements CidadeControllerOpenApiV2 {

	private CidadeRepository cidadeRepository;

	private CadastroCidadeService cadastroCidade;

	private CidadeDTOAssemblerV2 cidadeDTOAssembler;

	private CidadeInputDTODisassemblerV2 cidadeInputDTODisassembler;

	@GetMapping
	public CollectionModel<CidadeDTOV2> listar() {

		CollectionModel<CidadeDTOV2> cidadesCollectionModel = cidadeDTOAssembler
				.toCollectionModel(cidadeRepository.findAll());

		return cidadesCollectionModel;
	}

	@GetMapping("/{cidadeId}")
	public CidadeDTOV2 buscar(@PathVariable Long cidadeId) {

		CidadeDTOV2 cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
		return cidadeDTO;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CidadeDTOV2 adicionar(@RequestBody @Valid CidadeInputDTOV2 cidadeInputDTO) {
		try {

			Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInputDTO);

			cidade = cadastroCidade.salvar(cidade);

			CidadeDTOV2 cidadeDTO = cidadeDTOAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidade.getId());

			return cidadeDTO;

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeDTOV2 atualizar(@RequestBody @Valid CidadeInputDTOV2 cidadeInputDTO, @PathVariable Long cidadeId) {

		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		cidadeInputDTODisassembler.copyToDomainObject(cidadeInputDTO, cidadeAtual);

		try {

			return cidadeDTOAssembler.toModel(cadastroCidade.salvar(cidadeAtual));

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	public void excluir(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
