package com.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problema")
public class Problem {
	
	@Schema(example = "400")
	private Integer status;
	
	@Schema(example = "2022-07-15T11:21:50.902245498Z")
	private OffsetDateTime timestamp;
	
	@Schema(example = "https://algafood.com.br/dados-invalidos")
	private String type;
	
	@Schema(example = "Ddos invalidos")
	private String title;
	
	@Schema(example = "Um ou mais campos estão inválidos. Faça preenchimento correto e tente novamente")
	private String detail;
	
	@Schema(example = "Um ou mais campos estão inválidos. Faça preenchimento correto e tente novamente")
	private String userMessage;
	
	@Schema(description = "Lista de objetos com campos que geraram um erro")
	private List<Object> objects;
	
	@Getter
	@Builder
	@Schema(name = "ObjetoProblema")
	public static class Object {
	
		@Schema(name = "preco")
		private String name;
		
		@Schema(example = "O preço é inválido")
		private String userMessage;
	}
}
