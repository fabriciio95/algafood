package com.algafood.api.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.algafood.api.controller.CidadeController;
import com.algafood.api.controller.CozinhaController;
import com.algafood.api.controller.EstadoController;
import com.algafood.api.controller.EstatisticasController;
import com.algafood.api.controller.FluxoPedidoController;
import com.algafood.api.controller.FormaPagamentoController;
import com.algafood.api.controller.GrupoController;
import com.algafood.api.controller.GrupoPermissaoController;
import com.algafood.api.controller.PedidoController;
import com.algafood.api.controller.PermissaoController;
import com.algafood.api.controller.RestauranteController;
import com.algafood.api.controller.RestauranteFormaPagamentoController;
import com.algafood.api.controller.RestauranteProdutoController;
import com.algafood.api.controller.RestauranteProdutoFotoController;
import com.algafood.api.controller.RestauranteUsuarioController;
import com.algafood.api.controller.UsuarioController;
import com.algafood.api.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {

	private static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	private static final TemplateVariables PROJECAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM)
	);

	public Link linkToPedidos(String rel) {
		// pedidoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		return new Link(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), rel);
	}
	
	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscarPorId(usuarioId)).withRel(rel);
	}
	
	public Link linkToUsuario(Long usuarioId) {
		return linkToUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsuarios(String rel) {
		return linkTo(methodOn(UsuarioController.class).listar()).withRel(rel);
	}
	
	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null)).withRel(rel);
	}
	
	
	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId)).withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscarPorId(restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCidades(String rel) {
		return linkTo(methodOn(CidadeController.class).listar()).withRel(rel);
	}
	
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId)).withRel(rel);
	}

	public Link linkToEstado(Long estadoId) {
		return linkToEstado(estadoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEstados(String rel) {
		return linkTo(methodOn(EstadoController.class).listar()).withRel(rel);
	}
	
	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(methodOn(CozinhaController.class).listar(null)).withRel(rel);
	}
	
	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGruposUsuarios(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}
	
	public Link linkToGruposUsuarios(Long usuarioId) {
		return linkToGruposUsuarios(usuarioId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class)
				.listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToResponsaveisRestaurante(Long restauranteId) {
		return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToConfirmacaoPedido(String codidoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmar(codidoPedido)).withRel(rel);
	}
	
	public Link linkToCancelamentoPedido(String codidoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelar(codidoPedido)).withRel(rel);
	}
	
	public Link linkToEntregaPedido(String codidoPedido, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregar(codidoPedido)).withRel(rel);
	}
	
	public Link linkToCozinha(Long cozinhaId, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withRel(rel);
	}
	
	public Link linkToCozinha(Long cozinhaId) {
		return linkToCozinha(cozinhaId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurantes(String rel) {		
		String restaurantesUrl = linkTo(methodOn(RestauranteController.class).listar()).toUri().toString();
		
		return new Link(UriTemplate.of(restaurantesUrl, PROJECAO_VARIABLES), rel);
	}
	
	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteFormasPagamento(Long restauranteId) {
		return linkToRestauranteFormasPagamento(restauranteId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteAtivacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteInativacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).inativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteAbertura(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrir(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteFechamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).fechar(restauranteId)).withRel(rel);
	}
	
	public Link linkToFormasPagamento(String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).listar(null)).withRel(rel);
	}
	
	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteFormaPagamentoDesassociacao(Long restauranteId, Long formaPagamentoId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.desassociar(restauranteId, formaPagamentoId)).withRel(rel);
	}
	
	public Link linkToRestauranteFormaPagamentoAssociacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class)
				.associar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelDesassociacao(Long restauranteId, Long usuarioId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class)
				.deassociarResponsavel(restauranteId, usuarioId)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelAssociacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class)
				.associarResponsavel(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToRestauranteProdutos(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).listar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToRestauranteProdutos(Long restauranteId) {
		return linkToRestauranteProdutos(restauranteId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToFotoProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToFotoProduto(Long restauranteId, Long produtoId) {
		return linkToFotoProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscarPorId(restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToRestauranteProduto(Long restauranteId, Long produtoId) {
		return linkToRestauranteProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupo(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoController.class).buscarPorId(grupoId)).withRel(rel);
	}
	
	public Link linkToGrupo(Long grupoId) {
		return linkToGrupo(grupoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupos(String rel) {
		return linkTo(methodOn(GrupoController.class).listar()).withRel(rel);
	}
	
	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupoPermissoes(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToPermissoes(String rel) {
		return linkTo(methodOn(PermissaoController.class).listar()).withRel(rel);
	}
	
	public Link linkToPermissoes() {
		return linkToPermissoes(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupoPermissaoAssociacao(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, null)).withRel(rel);
	}
	
	public Link linkToGrupoPermissaoDesassociacao(Long grupoId, Long permissaoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)).withRel(rel);
	}
	
	public Link linkToUsuarioGrupoAssociacao(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).asociarGrupo(usuarioId, null)).withRel(rel);
	}
	
	public Link linkToUsuarioGrupoDesassociacao(Long usuarioid, Long grupoId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desasociarGrupo(usuarioid, grupoId)).withRel(rel);
	}
	
	public Link linkToEstatisticas(String rel) {
		return linkTo(methodOn(EstatisticasController.class).estatisticas()).withRel(rel);
	}
	
	public Link linkToEstatisticasVendasDiarias(String rel) {
		TemplateVariables templateVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM),
				new TemplateVariable("timeOffset", VariableType.REQUEST_PARAM)
		);
				
		
		String uri = linkTo(methodOn(EstatisticasController.class)
				.consultarVendasDiarias(null, null)).withRel(rel).toUri().toString();
		
		return new Link(UriTemplate.of(uri, templateVariables), rel);
	}
	
}

