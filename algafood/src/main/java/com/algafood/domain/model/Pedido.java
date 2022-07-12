package com.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;

import com.algafood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String codigo;
	
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@CreationTimestamp
	@Column(nullable = false)
	private OffsetDateTime dataCriacao; 
	
	private OffsetDateTime dataConfirmacao;
	
	private OffsetDateTime dataCancelamento;
	
	private OffsetDateTime dataEntrega;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;	
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public void calcularValorTotal() {
		 getItens().forEach(ItemPedido::calcularPrecoTotal);
		 
		 this.subtotal = getItens().stream()
				 			  .map(ItemPedido::getPrecoTotal)
				 			  .reduce(BigDecimal.ZERO, BigDecimal::add);
		 
		 this.valorTotal = subtotal.add(taxaFrete);
	}
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		dataConfirmacao = OffsetDateTime.now();
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		dataEntrega = OffsetDateTime.now();
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		dataCancelamento = OffsetDateTime.now();
	}
	
	private void setStatus(StatusPedido novoStatus) {
		if(status.naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(String.format("Status do pedido %s não pode ser alterado de %s para %s.",
					codigo, status.getDescricao(), novoStatus.getDescricao()));
		}
		
		status = novoStatus;
	}
	
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
}
