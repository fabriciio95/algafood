package com.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
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

import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable Long id) {
		return cadastroRestaurante.buscarOuFalhar(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		try {

			return cadastroRestaurante.salvar(restaurante);
			
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public Restaurante atualizar(@RequestBody Restaurante restaurante, @PathVariable Long id) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");
		
		try {

			return cadastroRestaurante.salvar(restauranteAtual);
			
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@RequestBody Map<String, Object> campos, @PathVariable Long id) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

		merge(campos, restauranteAtual);

		return atualizar(restauranteAtual, id);
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

		camposOrigem.forEach((campo, valor) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, campo);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		cadastroRestaurante.excluir(id);
	}
}
