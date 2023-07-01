package com.algafood.domain.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiaria {

	@Schema(example = "2019-12-01T10:00:00Z")
	private Date data;
	
	@Schema(example = "912")
	private Long totalVendas;
	
	@Schema(example = "15938.99")
	private BigDecimal totalFaturado;

	public VendaDiaria(java.sql.Date data, Long totalVendas, BigDecimal totalFaturado) {
		this.data = new Date(data.getTime());
		this.totalVendas = totalVendas;
		this.totalFaturado = totalFaturado;
	}
}
