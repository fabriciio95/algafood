package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.GrupoDTOAssembler;
import com.algafood.api.assembler.GrupoInputDTODisassembler;
import com.algafood.api.model.GrupoDTO;
import com.algafood.api.model.input.GrupoInputDTO;
import com.algafood.domain.model.Grupo;
import com.algafood.domain.repository.GrupoRepository;
import com.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private GrupoInputDTODisassembler grupoInputDTODisassembler;
	
	
	@GetMapping
	public List<GrupoDTO> listar() {
		return grupoDTOAssembler.toListDTO(grupoRepository.findAll());
	}
	
	@GetMapping("/{grupoId}")
	public GrupoDTO buscarPorId(@PathVariable Long grupoId) {
		return grupoDTOAssembler.toDTO(cadastroGrupoService.buscarOuFalhar(grupoId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInputDTO grupoInputDTO) {
		
		Grupo grupo = grupoInputDTODisassembler.toObjectDomain(grupoInputDTO);
		
		return grupoDTOAssembler.toDTO(cadastroGrupoService.salvar(grupo));
	}
	
	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@RequestBody @Valid GrupoInputDTO grupoInputDTO, @PathVariable Long grupoId) {
		
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		
		grupoInputDTODisassembler.copyToDomainObject(grupoInputDTO, grupo);
		
		return grupoDTOAssembler.toDTO(cadastroGrupoService.salvar(grupo));
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
