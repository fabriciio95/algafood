package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.model.Estado;
import com.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Estado> listar() {
		TypedQuery<Estado> query = entityManager.createQuery("from Estado", Estado.class);
		return query.getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return entityManager.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return entityManager.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Long estadoId) {
		Estado estado = buscar(estadoId);
		
		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		entityManager.remove(estado);
	}

}
