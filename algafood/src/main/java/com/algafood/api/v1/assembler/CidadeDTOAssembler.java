package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.CidadeController;
import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.domain.model.Cidade;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public CidadeDTOAssembler() {
		super(CidadeController.class, CidadeDTO.class);
	}

	@Override
	public CidadeDTO toModel(Cidade cidade) {
		CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, cidadeDTO);

		cidadeDTO.add(algaLinks.linkToCidades("cidades"));

		cidadeDTO.getEstado().add(algaLinks.linkToEstado(cidadeDTO.getEstado().getId()));

		return cidadeDTO;
	}

	@Override
	public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToCidades());
	}
}
