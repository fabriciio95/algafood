package com.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.PermissaoDTOAssembler;
import com.algafood.api.v1.model.PermissaoDTO;
import com.algafood.api.v1.openapi.controller.PermissaoControllerOpenAPI;
import com.algafood.domain.repository.PermissaoRepository;


@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenAPI {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@GetMapping
	public CollectionModel<PermissaoDTO> listar() {
		return permissaoDTOAssembler.toCollectionModel(permissaoRepository.findAll());
	}
}
