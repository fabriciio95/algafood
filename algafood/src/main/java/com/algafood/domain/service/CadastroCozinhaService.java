package com.algafood.domain.service;

import org.springframework.stereotype.Service;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CadastroCozinhaService {

	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
}
