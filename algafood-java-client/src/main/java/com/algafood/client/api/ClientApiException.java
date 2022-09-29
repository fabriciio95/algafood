package com.algafood.client.api;

import org.springframework.web.client.RestClientResponseException;

import com.algafood.client.model.Problem;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private Problem problem;

	public ClientApiException(String message, RestClientResponseException cause) {
		super(message, cause);
		
		deserialize(cause);
	}

	private void deserialize(RestClientResponseException cause) {
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
		mapper.registerModule(new JavaTimeModule());
		
		mapper.findAndRegisterModules();
		
		try {
			this.problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class);
		} catch (Exception e) {
			log.warn("Não foi possível desserializar a resposta em um problema", e);
		}
	}
	
}
