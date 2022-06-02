package com.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.algafood.api.model.mixin.CidadeMixin;
import com.algafood.api.model.mixin.CozinhaMixin;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}
	
}
