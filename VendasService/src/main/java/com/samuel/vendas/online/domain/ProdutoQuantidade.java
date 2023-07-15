package com.samuel.vendas.online.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProdutoQuantidade {

	@NotNull
	private Produto produto;
	
	@NotNull
	private Integer quantidade;
	
	private BigDecimal valorTotal;
	
	public ProdutoQuantidade() {
		quantidade = 0;
		valorTotal = BigDecimal.ZERO;
	}
	
	public void adicionar(Integer quantidade) {
		this.quantidade += quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.valorTotal = this.valorTotal.add(novoValor);
	}
	
	public void remover(Integer quantidade) {
		this.quantidade -= quantidade;
		BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
		this.valorTotal = this.valorTotal.subtract(novoValor);
	}
}
