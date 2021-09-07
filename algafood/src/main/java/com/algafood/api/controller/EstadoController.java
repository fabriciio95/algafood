package com.algafood.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}
}
