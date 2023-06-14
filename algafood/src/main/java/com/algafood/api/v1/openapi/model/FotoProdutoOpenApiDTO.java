package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "FotoProdutoDTO")
public class FotoProdutoOpenApiDTO {

	@Schema(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
	private String nomeArquivo;

	@Schema(example = "Prime Rib ao ponto")
	private String descricao;

	@Schema(example = "image/jpeg")
	private String contentType;

	@Schema(example = "202912")
	private Long tamanho;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
