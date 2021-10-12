package com.algafood.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.repository.CozinhaRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class TesteController {
	
	private CozinhaRepository cozinhaRepository;

//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
//		return cozinhaRepository.consultarPorNome(nome);
//	}

}
