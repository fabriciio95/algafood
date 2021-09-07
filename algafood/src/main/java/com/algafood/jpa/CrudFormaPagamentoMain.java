package com.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algafood.AlgafoodApplication;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.repository.FormaPagamentoRepository;

public class CrudFormaPagamentoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class).web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		formaPagamentoRepository.listar().forEach(System.out::println);
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setDescricao("Pix");
		
		formaPagamento = formaPagamentoRepository.salvar(formaPagamento);
		
		System.out.println(formaPagamentoRepository.buscar(formaPagamento.getId()));
		
		formaPagamento.setDescricao("Cheque");
		
		System.out.println(formaPagamentoRepository.salvar(formaPagamento));
		
		formaPagamentoRepository.remover(formaPagamento);
		
		formaPagamentoRepository.listar().forEach(System.out::println);
	}
}
