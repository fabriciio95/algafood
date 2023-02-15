package com.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.PermissaoDTOAssembler;
import com.algafood.api.v1.model.PermissaoDTO;
import com.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Grupo;
import com.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<PermissaoDTO> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		 CollectionModel<PermissaoDTO> permissoes = permissaoDTOAssembler.toCollectionModel(grupo.getPermissoes());
		 
		 permissoes.removeLinks();
		 
		 if(algaSecurity.podeConsultarUsuariosGruposPermissoes())
			 permissoes.add(algaLinks.linkToGrupoPermissoes(grupoId));
		 
		 if(algaSecurity.podeEditarUsuariosGruposPermissoes()) {
			 permissoes.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
			 
			 permissoes.forEach(permissao -> {
				 permissao.add(algaLinks.linkToGrupoPermissaoDesassociacao(grupoId, permissao.getId(), "desassociar"));
			 });
		 }
		 
		 return permissoes;
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{permissaoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.desassociar(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{permissaoId}")
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupoService.associar(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
}
