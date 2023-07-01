package com.algafood.api.v1.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.algafood.api.v1.assembler.GrupoDTOAssembler;
import com.algafood.api.v1.assembler.GrupoInputDTODisassembler;
import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.model.input.GrupoInputDTO;
import com.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Grupo;
import com.algafood.domain.repository.GrupoRepository;
import com.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private GrupoInputDTODisassembler grupoInputDTODisassembler;
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<GrupoDTO> listar() {
		return grupoDTOAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeConsultar
	@Override
	@GetMapping("/{grupoId}")
	public GrupoDTO buscarPorId(@PathVariable Long grupoId) {
		return grupoDTOAssembler.toModel(cadastroGrupoService.buscarOuFalhar(grupoId));
	}
	
	@Override
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInputDTO grupoInputDTO) {
		
		Grupo grupo = grupoInputDTODisassembler.toObjectDomain(grupoInputDTO);
		
		return grupoDTOAssembler.toModel(cadastroGrupoService.salvar(grupo));
	}
	
	@Override
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@RequestBody @Valid GrupoInputDTO grupoInputDTO, @PathVariable Long grupoId) {
		
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		grupoInputDTODisassembler.copyToDomainObject(grupoInputDTO, grupo);
		
		return grupoDTOAssembler.toModel(cadastroGrupoService.salvar(grupo));
	}
	
	@Override
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
