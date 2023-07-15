package com.samuel.vendas.online.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.samuel.vendas.online.domain.Venda;

public interface IVendaRepository extends MongoRepository<Venda, String> {

	Optional<Venda> findByCodigo(String codigo);
}
