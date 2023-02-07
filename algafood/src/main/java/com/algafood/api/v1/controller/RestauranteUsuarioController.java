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

import com.algafood.api.v1.assembler.UsuarioDTOAssembler;
import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.api.v1.openapi.controller.RestauranteUsuarioControllerOpenApi;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {

	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		 CollectionModel<UsuarioDTO> usuariosDTO = usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(algaLinks.linkToResponsaveisRestaurante(restauranteId))
				.add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));
		 
		 usuariosDTO.forEach(usuarioDTO -> {
			 usuarioDTO.add(algaLinks
					 .linkToRestauranteResponsavelDesassociacao(restauranteId, usuarioDTO.getId(), "desassociar"));
		 });
		 
		 return usuariosDTO;
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Void> associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> deassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}
