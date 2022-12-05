package com.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v2.controller.CidadeControllerV2;
import com.algafood.api.v2.model.CidadeDTOV2;
import com.algafood.api.v2.utils.AlgaLinksV2;
import com.algafood.domain.model.Cidade;

@Component
public class CidadeDTOAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeDTOV2> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinksV2 algaLinks;

	public CidadeDTOAssemblerV2() {
		super(CidadeControllerV2.class, CidadeDTOV2.class);
	}

	@Override
	public CidadeDTOV2 toModel(Cidade cidade) {
		CidadeDTOV2 cidadeDTO = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, cidadeDTO);

		cidadeDTO.add(algaLinks.linkToCidades("cidades"));

		return cidadeDTO;
	}

	@Override
	public CollectionModel<CidadeDTOV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToCidades());
	}
}
