package com.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.UsuarioDTOAssembler;
import com.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.api.v1.model.input.SenhaInputDTO;
import com.algafood.api.v1.model.input.UsuarioComSenhaInputDTO;
import com.algafood.api.v1.model.input.UsuarioInputDTO;
import com.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.UsuarioRepository;
import com.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	
	@GetMapping
	public CollectionModel<UsuarioDTO> listar() {
		return usuarioDTOAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscarPorId(@PathVariable Long usuarioId) {
		return usuarioDTOAssembler.toModel(cadastroUsuarioService.buscarOuFalhar(usuarioId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaInputDTO usuarioInputDTO) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInputDTO);
		
		return usuarioDTOAssembler.toModel(cadastroUsuarioService.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@RequestBody @Valid UsuarioInputDTO usuarioInputDTO, @PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		
		usuarioInputDisassembler.copyToDomainObject(usuarioInputDTO, usuario);
		
		return usuarioDTOAssembler.toModel(cadastroUsuarioService.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@RequestBody @Valid SenhaInputDTO senhaInputDTO, @PathVariable Long usuarioId) {
		cadastroUsuarioService.alterarSenha(usuarioId, senhaInputDTO.getSenhaAtual(), senhaInputDTO.getNovaSenha());
	}
}
