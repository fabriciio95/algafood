package com.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.api.v1.model.FotoProdutoDTO;
import com.algafood.api.v1.model.input.FotoProdutoInputDTO;

public interface RestauranteProdutoFotoControllerOpenApi {

	FotoProdutoDTO buscar(Long restauranteId,  Long produtoId);

	ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId,  String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	FotoProdutoDTO atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInputDTO fotoProdutoInput, MultipartFile arquivo)
			throws IOException;

	
	void excluir(Long restauranteId, Long produtoId);

}