package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

	private CidadeRepository cidadeRepository;

	private CadastroCidadeService cadastroCidade;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return cadastroCidade.buscarOuFalhar(cidadeId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
		try {
			
			return cadastroCidade.salvar(cidade);
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@RequestBody @Valid Cidade cidade, @PathVariable Long cidadeId) {
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		try {
			
			return cadastroCidade.salvar(cidadeAtual);
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	public void excluir(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
