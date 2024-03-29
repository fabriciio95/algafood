package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.controller.RestauranteController;
import com.algafood.api.v1.model.RestauranteDTO;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Restaurante;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	public RestauranteDTOAssembler() {
		super(RestauranteController.class, RestauranteDTO.class);
	}
	
	@Override
	public RestauranteDTO toModel(Restaurante restaurante) {
		 RestauranteDTO restauranteDTO = createModelWithId(restaurante.getId(), restaurante);
		  
		 modelMapper.map(restaurante, restauranteDTO);
		 
		 if(algaSecurity.podeConsultarCozinhas()) {
			 restauranteDTO.getCozinha().add(algaLinks.linkToCozinha(restauranteDTO.getCozinha().getId()));
		 }
		 
		 if(algaSecurity.podeConsultarCidades()) {
			 if(restauranteDTO.getEndereco() != null && restauranteDTO.getEndereco().getCidade() != null)
				 	restauranteDTO.getEndereco().getCidade()
			 					.add(algaLinks.linkToCidade(restauranteDTO.getEndereco().getCidade().getId()));
		 }
		 
		 if(algaSecurity.podeConsultarRestaurantes()) {
			 restauranteDTO.add(algaLinks.linkToRestaurantes("restaurantes"));
			 
			 restauranteDTO.add(algaLinks.linkToRestauranteFormasPagamento(restauranteDTO.getId(), "formas-pagamento"));
			 
			 restauranteDTO.add(algaLinks.linkToRestauranteProdutos(restauranteDTO.getId(), "produtos"));
		 }
		 
		if(algaSecurity.podeGerenciarCadastroRestaurantes()) {
			restauranteDTO.add(algaLinks.linkToResponsaveisRestaurante(restauranteDTO.getId(), "responsaveis"));
		}
		 
		 if(algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
			 if(restaurante.fechamentoPermitido()) 
				 restauranteDTO.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
			 
			 if(restaurante.aberturaPermitida())
				 restauranteDTO.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
		 }
		 
		 if(algaSecurity.podeGerenciarCadastroRestaurantes()) {
			 
			 if(restaurante.inativacaoPermitida())
				 restauranteDTO.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
			 
			 if(restaurante.ativacaoPermitida())
				 restauranteDTO.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
		 }
		  
		 
		 return restauranteDTO;
	}
	
	 @Override
	 public CollectionModel<RestauranteDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
	      CollectionModel<RestauranteDTO> collectionDTO = super.toCollectionModel(entities);
	      
	      if(algaSecurity.podeConsultarRestaurantes()) {
	    	  collectionDTO.add(algaLinks.linkToRestaurantes());
	      }
	        
	      return collectionDTO;  
	 } 
}
