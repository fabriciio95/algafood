package com.algafood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.UsuarioDTOAssembler;
import com.algafood.api.model.UsuarioDTO;
import com.algafood.api.openapi.controller.RestauranteUsuarioControllerOpenApi;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {

	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@GetMapping
	public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		return usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(linkTo(methodOn(RestauranteUsuarioController.class).listar(restauranteId)).withSelfRel());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{usuarioId}")
	public void associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{usuarioId}")
	public void deassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
	}
}
