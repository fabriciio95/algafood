package com.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
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
	public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if(cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {

			cidade = cadastroCidade.salvar(cidade);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Cidade cidade, @PathVariable Long id) {
		try {
			
			Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
			
			if(cidadeAtual.isPresent()) {
				
				BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
				
				Cidade cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
				
				return ResponseEntity.ok(cidadeSalva);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch(EntidadeNaoEncontradaException e) {
		
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			
			cadastroCidade.excluir(id);
			
			return ResponseEntity.noContent().build();
			
		} catch(EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			
		}
	}
}
