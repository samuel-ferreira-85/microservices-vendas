package com.samuel.vendas.online.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {
	
	
	private String id;
	
	@NotNull
	@Size(min = 2, max = 10)
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String nome;
	
	@NotNull
	private String descricao;
	
	private BigDecimal valor;
	
}