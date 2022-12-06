package com.algafood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algafood.api.v2.assembler.CozinhaDTOAssemblerV2;
import com.algafood.api.v2.assembler.CozinhaDTODisassemblerV2;
import com.algafood.api.v2.model.CozinhaDTOV2;
import com.algafood.api.v2.model.input.CozinhaInputDTOV2;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(path = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CozinhaDTOAssemblerV2 cozinhaDTOAssembler;
	
	@Autowired
	private CozinhaDTODisassemblerV2 cozinhaDTODisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<CozinhaDTOV2> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaDTOV2> cozinhasPagedModel = pagedResourcesAssembler
														.toModel(cozinhasPage, cozinhaDTOAssembler);
		
		return cozinhasPagedModel;
		
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaDTOV2 buscar(@PathVariable Long cozinhaId) {
		return cozinhaDTOAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaDTOV2 adicionar(@RequestBody @Valid CozinhaInputDTOV2 cozinhaInputDTO) {
		
		Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaInputDTO);
		
		return cozinhaDTOAssembler.toModel(cadastroCozinha.salvar(cozinha));
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaDTOV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputDTOV2 cozinhaInputDTO) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		cozinhaDTODisassembler.copyToDomainObject(cozinhaInputDTO, cozinhaAtual);
		
		return cozinhaDTOAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
	}


	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}
}
