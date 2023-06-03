package com.algafood.core.springdoc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.openapi.model.CozinhasDTOOpenApi;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.Schema;

public class CustomModelConverter implements ModelConverter {

	@Override
	@SuppressWarnings("rawtypes")
	public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
		
		if(type.getType() instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type.getType();
			
			if(parameterizedType.getRawType().equals(PagedModel.class)) {
				
				Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				
				if(actualTypeArguments.length == 1 && actualTypeArguments[0].equals(CozinhaDTO.class)) {
					 AnnotatedType novoTipo = new AnnotatedType().type(CozinhasDTOOpenApi.class);
					 return context.resolve(novoTipo);
				}
			}
			
		}
		if(chain.hasNext()) {
			return chain.next().resolve(type, context, chain);
		}
		
		return null;
	}

}
