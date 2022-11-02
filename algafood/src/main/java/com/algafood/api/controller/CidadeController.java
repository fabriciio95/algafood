package com.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.CidadeDTOAssembler;
import com.algafood.api.assembler.CidadeInputDTODisassembler;
import com.algafood.api.model.CidadeDTO;
import com.algafood.api.model.input.CidadeInputDTO;
import com.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algafood.api.utils.ResourceUriHelper;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CidadeController implements CidadeControllerOpenApi {

	private CidadeRepository cidadeRepository;

	private CadastroCidadeService cadastroCidade;
	
	private CidadeDTOAssembler cidadeDTOAssembler;
	
	private CidadeInputDTODisassembler cidadeInputDTODisassembler;

	@GetMapping
	public CollectionModel<CidadeDTO> listar() {		 
		 
//		 cidadesCollectionModel.forEach(cidadeDTO -> {
//			 cidadeDTO.add(linkTo(methodOn(CidadeController.class)
//					 .buscar(cidadeDTO.getId())).withSelfRel());
//			 
//			 cidadeDTO.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
//			 
//			 cidadeDTO.getEstado().add(linkTo(methodOn(EstadoController.class)
//					 .buscar(cidadeDTO.getEstado().getId())).withSelfRel());
//		 });
//		 
//		 cidadesCollectionModel.add(linkTo(methodOn(CidadeController.class).listar()).withSelfRel());
		 
		 CollectionModel<CidadeDTO> cidadesCollectionModel = cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAll());

		 return cidadesCollectionModel;
	}

	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable Long cidadeId) {

		 
//		 cidadeDTO.add(linkTo(methodOn(CidadeController.class)
//				 .buscar(cidadeDTO.getId())).withSelfRel());
//		 
//		 
////		 cidadeDTO.add(linkTo(CidadeController.class)
////				 						.slash(cidadeDTO.getId())
////				 						.withSelfRel());
////		 
//		 //cidadeDTO.add(new Link("http://api.algafood.local:8080/cidades/1"));
//		 
//		// cidadeDTO.add(new Link("http://api.algafood.local:8080/cidades", IanaLinkRelations.COLLECTION));
//		 
//		// cidadeDTO.add(new Link("http://api.algafood.local:8080/cidades", "cidades"));
//		 
////		 cidadeDTO.add(linkTo(CidadeController.class)
////				 						.withRel("cidades"));
//		 
//		 cidadeDTO.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));
//		 
//		// cidadeDTO.getEstado().add(new Link("http://api.algafood.local:8080/estados/1"));
//		 
////		 cidadeDTO.getEstado().add(linkTo(EstadoController.class)
////				 									.slash(cidadeDTO.getEstado().getId())
////				 									.withSelfRel());
//
//		 cidadeDTO.getEstado().add(linkTo(methodOn(EstadoController.class)
//				 .buscar(cidadeDTO.getEstado().getId())).withSelfRel());
		 CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
		 return cidadeDTO;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
		try {
			
			Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInputDTO);
			
			cidade = cadastroCidade.salvar(cidade);
			
			CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(cidade.getId());
			
			return cidadeDTO;
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO,
			@PathVariable Long cidadeId) {
		
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		cidadeInputDTODisassembler.copyToDomainObject(cidadeInputDTO, cidadeAtual);
		
		try {
			
			return cidadeDTOAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	public void excluir(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
