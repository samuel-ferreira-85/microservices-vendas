package com.samuel.vendas.online.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samuel.vendas.online.domain.Produto;

@FeignClient(name = "produto", url = "${application.produtoService.endpointConsultarProduto}")
public interface IProdutoService {

	@RequestMapping(value = "/{codigo}", produces = "application/json", headers = "application/json")
	Produto buscarProduto(@RequestParam("codigo") String codigoProduto);

}
