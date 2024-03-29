package com.algafood.api.v1.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.RestauranteApenasNomeDTOAssembler;
import com.algafood.api.v1.assembler.RestauranteBasicoDTOAssembler;
import com.algafood.api.v1.assembler.RestauranteDTOAssembler;
import com.algafood.api.v1.assembler.RestauranteInputDTODisassembler;
import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.model.RestauranteDTO;
import com.algafood.api.v1.model.input.RestauranteInputDTO;
import com.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.core.validation.ValidacaoException;
import com.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/v1/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private SmartValidator validator;
	
	@Autowired
	private RestauranteDTOAssembler restauranteDTOAssembler;
	
	@Autowired
	private RestauranteInputDTODisassembler restauranteInputDtoDisassembler;
	
	@Autowired
	private RestauranteBasicoDTOAssembler restauranteBasicoDTOAssembler;
	
	@Autowired
	private RestauranteApenasNomeDTOAssembler restauranteApenasNomeDTOAssembler;

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public CollectionModel<RestauranteBasicoDTO> listar() {
		return restauranteBasicoDTOAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(params = "projecao=apenas-nome")
	public CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes() {
		return restauranteApenasNomeDTOAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscar(@PathVariable Long restauranteId) {
	   Restaurante restaurante =  cadastroRestaurante.buscarOuFalhar(restauranteId);
	   	     
	   return restauranteDTOAssembler.toModel(restaurante);
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		try {
			
			Restaurante restaurante = restauranteInputDtoDisassembler.toDomainObject(restauranteInputDTO);

			return restauranteDTOAssembler.toModel(cadastroRestaurante.salvar(restaurante));

		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO, 
			@PathVariable Long restauranteId) {
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		restauranteInputDtoDisassembler.copyToDomainObject(restauranteInputDTO, restauranteAtual);

		try {

			return restauranteDTOAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));

		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/ativo")
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{restauranteId}/ativo")
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/ativacoes")
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.ativar(restauranteIds);
		} catch(RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/ativacoes")
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			cadastroRestaurante.inativar(restauranteIds);
		} catch(RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/fechamento")
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fecharRestaurante(restauranteId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/abertura")
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrirRestaurante(restauranteId);
		
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PatchMapping("/{restauranteId}")
	public RestauranteDTO atualizarParcial(@RequestBody Map<String, Object> campos, @PathVariable Long restauranteId,
			HttpServletRequest request) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		merge(campos, restauranteAtual, request);
		
		validate(restauranteAtual, "restaurante");

		return atualizar(new RestauranteInputDTO(restauranteAtual), restauranteId);
	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		
		validator.validate(restaurante, bindingResult);
		
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}
	

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = mapper.convertValue(camposOrigem, Restaurante.class);

			camposOrigem.forEach((campo, valor) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, campo);
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			HttpInputMessage httpMessage = new ServletServerHttpRequest(request);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, httpMessage);
		}
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{restauranteId}")
	public void excluir(@PathVariable Long restauranteId) {
		cadastroRestaurante.excluir(restauranteId);
	}
}
