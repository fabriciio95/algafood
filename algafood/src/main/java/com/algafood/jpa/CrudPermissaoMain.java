package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.Permissao;
import com.algafood.domain.repository.PermissaoRepository;

public class CrudPermissaoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		permissaoRepository.listar().forEach(System.out::println);
		
		Permissao permissao = new Permissao();
		permissao.setNome("Admin");
		permissao.setDescricao("Possui privilégios de administrador");
		
		permissao = permissaoRepository.salvar(permissao);
		
		System.out.println(permissaoRepository.buscar(permissao.getId()));
		
		permissao.setNome("Usuário");
		permissao.setDescricao("Possui privilégios de usuário");
		
		System.out.println(permissaoRepository.salvar(permissao));
		
		permissaoRepository.remover(permissao);
		
		permissaoRepository.listar().forEach(System.out::println);
	}
}
