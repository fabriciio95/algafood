package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algafood.domain.model.Grupo;
import com.algafood.domain.model.Permissao;
import com.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser excluído pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long grupoId) {
		
		try {
		
			grupoRepository.deleteById(grupoId);
			
			grupoRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}
	
	@Transactional
	public void desassociar(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		
		grupo.removerPermissao(permissao);
	}
	
	@Transactional
	public void associar(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		
		grupo.adicionarPermissao(permissao);
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId)
				.orElseThrow(() ->  new GrupoNaoEncontradoException(grupoId));
	}
}
