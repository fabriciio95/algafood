package com.algafood.api.v2.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.algafood.api.v2.controller.CidadeControllerV2;
import com.algafood.api.v2.controller.CozinhaControllerV2;

@Component
public class AlgaLinksV2 {

	
	public Link linkToCidades(String rel) {
		return linkTo(methodOn(CidadeControllerV2.class).listar()).withRel(rel);
	}
	
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(methodOn(CozinhaControllerV2.class).listar(null)).withRel(rel);
	}
	
	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}

}

