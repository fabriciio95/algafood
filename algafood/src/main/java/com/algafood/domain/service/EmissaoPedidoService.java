package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.Produto;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		
		validarPedido(pedido);
		
		validarItens(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		
		pedido.calcularValorTotal();
				
		return pedidoRepository.save(pedido);
	}
	
		
	private void validarPedido(Pedido pedido) {
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
		Usuario cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
		
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setCliente(cliente);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		
		if(restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
	                formaPagamento.getDescricao()));
		}
	}


	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item ->  {
				Produto produto = cadastroProdutoService.buscarOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());
				
				item.setPedido(pedido);
				item.setProduto(produto);
				item.setPrecoUnitario(produto.getPreco());
			});
	}
	
	public Pedido buscaOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}
}
