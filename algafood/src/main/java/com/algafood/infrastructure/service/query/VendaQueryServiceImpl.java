package com.algafood.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.StatusPedido;
import com.algafood.domain.model.dto.VendaDiaria;
import com.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
//		select date(convert_tz(p.data_criacao, '+00:00', '-03:00')) as data_criacao,
//			count(p.id) as total_vendas,
//			sum(p.valor_total) as total_faturado
//		from pedido p
//		where p.status in ('CONFIRMADO', 'ENTREGUE')
//		group by date(convert_tz(p.data_criacao, '+00:00', '-03:00'));
		
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);
		
		var functionConvertTzDataCriacao = builder.function(
				"convert_tz",
				Date.class,
				root.get("dataCriacao"),
				builder.literal("+00:00"),
				builder.literal(timeOffset));
		
		var functionDateDataCriacao = builder.function("date", Date.class, functionConvertTzDataCriacao);
		
		var selection = builder.construct(VendaDiaria.class, 
				functionDateDataCriacao, 
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		
		query.groupBy(functionDateDataCriacao);
		
		var predicates = new ArrayList<>();
		
		if(filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}
		
		if(filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataEntrega"), filtro.getDataCriacaoFim()));
		}
		
		if(filtro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
		}
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		
		query.where(builder.and(predicates.toArray(new Predicate[0])));
		
		return manager.createQuery(query).getResultList();
	}

}
