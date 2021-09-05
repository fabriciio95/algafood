package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.repository.EstadoRepository;

public class CrudCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		cidadeRepository.listar().forEach(System.out::println);
		
		Estado estado = estadoRepository.buscar(1L);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Osasco");
		cidade.setEstado(estado);
		
		cidade = cidadeRepository.salvar(cidade);
		
		System.out.println(cidadeRepository.buscar(cidade.getId()));
		
		estado = estadoRepository.buscar(2L);
		cidade.setEstado(estado);
		
		System.out.println(cidadeRepository.salvar(cidade));
		
		cidadeRepository.remover(cidade);
		
		cidadeRepository.listar().forEach(System.out::println);
	}
}
