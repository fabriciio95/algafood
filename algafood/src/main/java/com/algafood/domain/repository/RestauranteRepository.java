package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
		JpaSpecificationExecutor<Restaurante> {
	
	@Query("select distinct r from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	

	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

	Optional<Restaurante> getFirstByNomeContaining(String nome);

	List<Restaurante> readTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);
}
