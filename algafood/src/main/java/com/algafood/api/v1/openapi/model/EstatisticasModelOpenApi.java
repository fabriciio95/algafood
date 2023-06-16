package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EstatisticasDTO")
public class EstatisticasModelOpenApi {

	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
