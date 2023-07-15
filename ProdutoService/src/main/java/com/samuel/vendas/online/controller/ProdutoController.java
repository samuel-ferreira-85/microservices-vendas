package com.samuel.vendas.online.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.vendas.online.domain.Produto;
import com.samuel.vendas.online.domain.Produto.Status;
import com.samuel.vendas.online.services.ProdutosService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	private ProdutosService produtoService;

	public ProdutoController(ProdutosService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping
	@Operation(summary = "Busca uma lista paginada de produtos.")
	public ResponseEntity<Page<Produto>> buscar(Pageable pageable) {
		return ResponseEntity.ok(produtoService.buscar(pageable));
	}
	
	@GetMapping(value = "/status/{status}")
	@Operation(summary = "Busca uma lista paginada de produtos por status.")
	public ResponseEntity<Page<Produto>> buscarPorStatus(Pageable pageable, 
			@PathVariable(value = "status", required = true) Status status) {
		return ResponseEntity.ok(produtoService.buscar(pageable, status));
	}
	
	@GetMapping(value = "/{codigo}")
	@Operation(summary = "Busca um produto pelo código.")
	public ResponseEntity<Produto> bucarPorCodigo(@PathVariable(value = "codigo", required = true) String codigo) {
		return ResponseEntity.ok(produtoService.burcarPorCodigo(codigo));
	}
	
	@PostMapping
	@Operation(summary = "Cadastra um produto")
	public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto ) {
		return ResponseEntity.ok(produtoService.cadastrar(produto));
	}
	
	@PutMapping
	@Operation(summary = "Atualiza um produto")
	public ResponseEntity<Produto> atualizar(@RequestBody @Valid Produto produto ) {
		return ResponseEntity.ok(produtoService.atualizar(produto));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove um produto pelo ID")
	public ResponseEntity<String> remover(@PathVariable(value = "id", required = true) String id) {
		produtoService.remover(id);
		return ResponseEntity.ok("Produto removido com sucesso.");
	}
	
}
