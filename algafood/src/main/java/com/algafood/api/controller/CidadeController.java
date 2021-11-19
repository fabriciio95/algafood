package com.algafood.api.controller;

import java.util.List;

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

	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id) {
		return cadastroCidade.buscarOuFalhar(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return cadastroCidade.salvar(cidade);
	}

	@PutMapping("/{id}")
	public Cidade atualizar(@RequestBody Cidade cidade, @PathVariable Long id) {
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		return cadastroCidade.salvar(cidadeAtual);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		cadastroCidade.excluir(id);

	}
}
