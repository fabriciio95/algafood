package com.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.RestauranteRepository;


@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> listar() {
		TypedQuery<Restaurante> restaurantes =  entityManager.createQuery("from Restaurante", Restaurante.class);
		return restaurantes.getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return entityManager.find(Restaurante.class, id);
	}

	
	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return entityManager.merge(restaurante);
	}

	@Override
	@Transactional
	public void remover(Long restauranteId) {
		Restaurante restaurante = buscar(restauranteId);
		
		if(restaurante == null) 
			throw new EmptyResultDataAccessException(1);
		
		entityManager.remove(restaurante);
	}	
}
