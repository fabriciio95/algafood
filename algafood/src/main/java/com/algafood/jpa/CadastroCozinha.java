package com.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Cozinha> listar() {
		TypedQuery<Cozinha> query =  entityManager.createQuery("from Cozinha", Cozinha.class);
		return query.getResultList();
	}
	
	public Cozinha buscar(Long id) {
		return entityManager.find(Cozinha.class, id);
	}
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		 return entityManager.merge(cozinha);
	}
}
