package com.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
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

import com.algafood.api.model.RestauranteDTO;
import com.algafood.api.model.input.RestauranteInputDTO;
import com.algafood.core.validation.ValidacaoException;
import com.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private SmartValidator validator;

	@GetMapping
	public List<RestauranteDTO> listar() {
		return restauranteRepository.findAll().stream().map(RestauranteDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscar(@PathVariable Long restauranteId) {
	   Restaurante restaurante =  cadastroRestaurante.buscarOuFalhar(restauranteId);
	   	     
	   return new RestauranteDTO(restaurante);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		try {

			return new RestauranteDTO(cadastroRestaurante.salvar(restauranteInputDTO.toModel()));

		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteDTO atualizar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO, 
			@PathVariable Long restauranteId) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

		BeanUtils.copyProperties(restauranteInputDTO, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		try {

			return new RestauranteDTO(cadastroRestaurante.salvar(restauranteAtual));

		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

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

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{restauranteId}")
	public void excluir(@PathVariable Long restauranteId) {
		cadastroRestaurante.excluir(restauranteId);
	}
}
