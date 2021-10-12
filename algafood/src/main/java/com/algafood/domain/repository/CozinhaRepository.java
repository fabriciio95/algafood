package com.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Cozinha;


@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	List<Cozinha> nome(String nome);
	
	List<Cozinha> findQualquerCoisaByNome(String nome);
	
	Optional<Cozinha> findByNome(String nome);
}
