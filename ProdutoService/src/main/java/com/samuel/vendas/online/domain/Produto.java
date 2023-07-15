package com.samuel.vendas.online.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Produto", description = "Produto")
public class Produto {
	
	@Id
	@Schema(description = "Identificador único")
	private String id;
	
	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(background = true, unique = true)
	@Schema(description="Código", nullable = false)
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="Nome", nullable = false)
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description="Descrição", nullable = false)
	private String descricao;
	
	@Schema(description="Valor do produto")
	private BigDecimal valor;
	
	@Schema(description="Status do produto")
	private Status status;
	
	public enum Status {
		ATIVO, INATIVO;
	}
}
