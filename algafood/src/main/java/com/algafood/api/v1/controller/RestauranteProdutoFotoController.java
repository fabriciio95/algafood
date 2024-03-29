package com.algafood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.api.v1.assembler.FotoProdutoAssembler;
import com.algafood.api.v1.model.FotoProdutoDTO;
import com.algafood.api.v1.model.input.FotoProdutoInputDTO;
import com.algafood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.model.FotoProduto;
import com.algafood.domain.model.Produto;
import com.algafood.domain.service.CadastroProdutoService;
import com.algafood.domain.service.CatalogoFotoProdutoService;
import com.algafood.domain.service.FotoStorageService;
import com.algafood.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private FotoProdutoAssembler fotoProdutoAssembler;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		return fotoProdutoAssembler.toModel(catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId));
	}
	
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		
		try {
			FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
			
			if(fotoRecuperada.temUrl()) {
				
				return ResponseEntity
							.status(HttpStatus.FOUND)
							.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
							.build();
				
			} else {
			
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			
			}
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @Valid FotoProdutoInputDTO fotoProdutoInput) throws IOException {
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
				
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		
		foto = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoAssembler.toModel(foto);
		
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	public void excluir(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		catalogoFotoProdutoService.excluir(restauranteId, produtoId);
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
		
	}
}
