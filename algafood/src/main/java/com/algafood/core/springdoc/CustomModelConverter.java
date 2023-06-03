package com.algafood.core.springdoc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.openapi.model.CidadesModelOpenApi;
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
			
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			
			if(parameterizedType.getRawType().equals(PagedModel.class)) {
				
				if(actualTypeArguments.length == 1 && actualTypeArguments[0].equals(CozinhaDTO.class)) {
					 AnnotatedType novoTipo = new AnnotatedType().type(CozinhasDTOOpenApi.class);
					 return context.resolve(novoTipo);
				}
				
			} else if(parameterizedType.getRawType().equals(CollectionModel.class)) {
				 if(actualTypeArguments.length == 1 && actualTypeArguments[0].equals(CidadeDTO.class)) {
					 AnnotatedType novoTipo = new AnnotatedType().type(CidadesModelOpenApi.class);
					 return context.resolve(novoTipo);
				}
			} 
		}
		
		return chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
	}

}
