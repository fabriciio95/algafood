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

import com.algafood.api.v1.assembler.GrupoDTOAssembler;
import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		
		 CollectionModel<GrupoDTO> grupos = grupoDTOAssembler.toCollectionModel(usuario.getGrupos())
				.removeLinks()
				.add(algaLinks.linkToGruposUsuarios(usuarioId))
				.add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));
		 
		 grupos.forEach(grupo -> {
			 grupo.add(algaLinks.linkToUsuarioGrupoDesassociacao(usuarioId, grupo.getId(), "desassociar"));
		 });
		 
		 return grupos;
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{grupoId}")
	public ResponseEntity<Void> desasociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{grupoId}")
	public ResponseEntity<Void> asociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}
	
}
