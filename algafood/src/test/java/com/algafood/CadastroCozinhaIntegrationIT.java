package com.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationIT {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	public void deveriaAtribuirIdQuandoCadastrarCozinhaComDadosCorretos() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveriaFalharQuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
	}
	
	@Test(expected = EntidadeEmUsoException.class)
	public void deveriaFalhar_QuandoExcluirCozinhaEmUso() {
		cadastroCozinha.excluir(1L);
	}
	
	@Test(expected = CozinhaNaoEncontradaException.class)
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		cadastroCozinha.excluir(1000L);
	}

}
