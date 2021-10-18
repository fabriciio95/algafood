package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	/*Prefixos para buscar: read, get, query, stream e find*/
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
	
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	Optional<Restaurante> getFirstByNomeContaining(String nome);
	
	List<Restaurante> readTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinhaId);
}
