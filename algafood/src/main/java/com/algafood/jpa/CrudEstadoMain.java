package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

public class CrudEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		estadoRepository.listar().forEach(System.out::println);
		
		Estado estado = new Estado();
		estado.setNome("Sergipe");
		
		estado = estadoRepository.salvar(estado);
		
		System.out.println(estadoRepository.buscar(estado.getId()));
		
		estado.setNome("Amazonas");
		
		System.out.println(estadoRepository.salvar(estado));
		
		estadoRepository.remover(estado);
		
		estadoRepository.listar().forEach(System.out::println);
	}
}
