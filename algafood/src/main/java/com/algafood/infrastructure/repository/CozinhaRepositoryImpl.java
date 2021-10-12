package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;


@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Cozinha> listar() {
		TypedQuery<Cozinha> cozinhas =  entityManager.createQuery("from Cozinha", Cozinha.class);
		return cozinhas.getResultList();
	}
	
	@Override
	public List<Cozinha> consultarPorNome(String nome) {
		return entityManager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
					.setParameter("nome", "%" + nome + "%")
					.getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return entityManager.find(Cozinha.class, id);
	}

	@Override
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		entityManager.remove(cozinha);
	}

}
