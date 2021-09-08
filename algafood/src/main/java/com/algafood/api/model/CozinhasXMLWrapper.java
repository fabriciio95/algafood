package com.algafood.api.model;

import java.util.List;

import com.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

//@JsonRootName("cozinhas")
@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasXMLWrapper {
	
	@JacksonXmlProperty(localName = "cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	//@JsonProperty("cozinha")
	@NonNull
	private List<Cozinha> cozinhas;
	
}
