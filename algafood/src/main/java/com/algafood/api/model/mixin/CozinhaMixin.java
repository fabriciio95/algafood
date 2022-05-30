package com.algafood.api.model.mixin;

import java.util.List;

import com.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes;
}
