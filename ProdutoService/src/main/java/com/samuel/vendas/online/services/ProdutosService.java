package com.samuel.vendas.online.services;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samuel.vendas.online.domain.Produto;
import com.samuel.vendas.online.domain.Produto.Status;
import com.samuel.vendas.online.repository.IProdutoRepository;

@Service
public class ProdutosService {

	private IProdutoRepository produtoRepository;

	public ProdutosService(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Page<Produto> buscar(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}
	
	public Page<Produto> buscar(Pageable pageable, Status status) {
		return produtoRepository.findAllByStatus(pageable, status);
	}
	
	public Produto burcarPorCodigo(String codigo) {
		return produtoRepository.findByCodigo(codigo)
				.orElseThrow(() -> new EntityNotFoundException("Código especificado não encontrado."));
	}
	
	public Produto cadastrar(@Valid Produto produto) {
		produto.setStatus(Status.ATIVO);
		return produtoRepository.insert(produto);
	}
	
	public Produto atualizar(@Valid Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void remover(String id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Recurso não encontrado para o ID informado."));
		produto.setStatus(Status.INATIVO);
		produtoRepository.save(produto);
	}
}
