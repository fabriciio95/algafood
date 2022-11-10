package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.RestauranteController;
import com.algafood.api.model.RestauranteDTO;
import com.algafood.api.utils.AlgaLinks;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public RestauranteDTOAssembler() {
		super(RestauranteController.class, RestauranteDTO.class);
	}
	
	@Override
	public RestauranteDTO toModel(Restaurante restaurante) {
		 RestauranteDTO restauranteDTO = createModelWithId(restaurante.getId(), restaurante);
		  
		 modelMapper.map(restaurante, restauranteDTO);
		 
		 restauranteDTO.getCozinha().add(algaLinks.linkToCozinha(restauranteDTO.getCozinha().getId()));
		 
		 restauranteDTO.getEndereco().getCidade()
		 				.add(algaLinks.linkToCidade(restauranteDTO.getEndereco().getCidade().getId()));
		 
		 restauranteDTO.add(algaLinks.linkToRestaurantes("restaurantes"));
		 
		 restauranteDTO.add(algaLinks.linkToRestauranteFormasPagamento(restauranteDTO.getId(), "formas-pagamento"));
		 
		 restauranteDTO.add(algaLinks.linkToResponsaveisRestaurante(restauranteDTO.getId(), "responsaveis"));
		 
		 if(restaurante.fechamentoPermitido()) 
			 restauranteDTO.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
		 
		 if(restaurante.aberturaPermitida())
			 restauranteDTO.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
		 
		 if(restaurante.inativacaoPermitida())
			 restauranteDTO.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
		 
		 if(restaurante.ativacaoPermitida())
			 restauranteDTO.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
		 
		 return restauranteDTO;
	}
	
	 @Override
	 public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
	        return super.toCollectionModel(entities)
	                .add(algaLinks.linkToRestaurantes());
	 } 
}
