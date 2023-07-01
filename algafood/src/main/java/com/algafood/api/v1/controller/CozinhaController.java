package com.algafood.api.v1.controller;

import jakarta.validation.Valid;

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

import com.algafood.api.v1.assembler.CozinhaDTOAssembler;
import com.algafood.api.v1.assembler.CozinhaDTODisassembler;
import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.input.CozinhaInputDTO;
import com.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.service.CadastroCozinhaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {
	
	//private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CozinhaDTOAssembler cozinhaDTOAssembler;
	
	@Autowired
	private CozinhaDTODisassembler cozinhaDTODisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@CheckSecurity.Cozinhas.PodeConsultar
	@Override
	@GetMapping
	public PagedModel<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
		log.info("Consultando cozinhas com paginas de {} registros...", pageable.getPageSize());
		
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaDTO> cozinhasPagedModel = pagedResourcesAssembler
														.toModel(cozinhasPage, cozinhaDTOAssembler);
		
		return cozinhasPagedModel;
		
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@Override
	@GetMapping("/{cozinhaId}")
	public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
		return cozinhaDTOAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaDTO adicionar(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO) {
		
		Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaInputDTO);
		
		return cozinhaDTOAssembler.toModel(cadastroCozinha.salvar(cozinha));
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@Override
	@PutMapping("/{cozinhaId}")
	public CozinhaDTO atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputDTO cozinhaInputDTO) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		cozinhaDTODisassembler.copyToDomainObject(cozinhaInputDTO, cozinhaAtual);
		
		return cozinhaDTOAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@Override
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}
}
