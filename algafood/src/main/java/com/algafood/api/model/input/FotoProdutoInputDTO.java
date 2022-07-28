package com.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.algafood.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInputDTO {

	@NotNull
	@FileSize(max = "500KB")
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
}