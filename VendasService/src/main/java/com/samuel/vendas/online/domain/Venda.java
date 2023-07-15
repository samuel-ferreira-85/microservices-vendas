package com.samuel.vendas.online.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "venda")
public class Venda {

	@Id
	private String id;
	
	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String codigo;
	
	@NotNull
	private String clienteId;
	
	private Set<ProdutoQuantidade> produtos;
	
	private BigDecimal valorTotal;
	
	@NotNull
	private Instant dataVenda;
	
	@NotNull
	private Status status;
	
	public Venda() {
		produtos = new HashSet<>();
	}
	
	public void adicionarProdutos(Produto produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidade> produtoOptional = produtos.stream().filter(p -> p.getProduto().getCodigo()
				.equals(produto.getId())).findAny();
		if (produtoOptional.isPresent()) {
			ProdutoQuantidade produtoQuantidade = produtoOptional.get();
			produtoQuantidade.adicionar(quantidade);
		} else {
			ProdutoQuantidade produtoA = ProdutoQuantidade.builder()
				.produto(produto)
				.valorTotal(BigDecimal.ZERO)
				.quantidade(0)
				.build();
			produtoA.adicionar(quantidade);
			produtos.add(produtoA);
		}
		recalcularValorTotalVenda();
	}
	
	public void removerProdutos(Produto produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidade> produtoOptional = produtos.stream().filter(p -> p.getProduto().getCodigo()
				.equals(produto.getId())).findAny();
		if (produtoOptional.isPresent()) {
			ProdutoQuantidade produtoQuantidade = produtoOptional.get();
			if (produtoQuantidade.getQuantidade() > quantidade) {
				produtoQuantidade.remover(quantidade);
				recalcularValorTotalVenda();
			} else {
				produtos.remove(produtoOptional.get());
			}
		}
	}
	
	public void removerTodosProdutos() {
		validarStatus();
		produtos.clear();
		valorTotal = BigDecimal.ZERO;
	}
	
	public Integer getQuantidadeTotalProdutos() {
		// Soma a quantidade getQuantidade() de todos os objetos ProdutoQuantidade
		int result = produtos.stream()
				  .reduce(0, (partialCountResult, prod) -> 
				  		partialCountResult + prod.getQuantidade(), Integer::sum);
		return result;
	}
	
	public void recalcularValorTotalVenda() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ProdutoQuantidade pq : this.produtos) {
			valorTotal = valorTotal.add(pq.getValorTotal());
		}
		this.valorTotal = valorTotal;
	}
	
	public void validarStatus() {
		if (status == Status.CONCLUIDA || status == Status.CANCELADA) {
			throw new UnsupportedOperationException("Impossível alterar venda concluida ou finalizada!");
		}
	}
	
	public enum Status {
		INICIADA, CONCLUIDA, CANCELADA;
		
		public static Status getByName(String value) {
			for (Status status : Status.values()) {
				if (status.equals(value)) {
					return status;
				} 
			}
			return null;
		}
	}
}
