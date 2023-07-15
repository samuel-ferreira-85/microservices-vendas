package com.samuel.vendas.online.usecase;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.samuel.vendas.online.domain.Cliente;
import com.samuel.vendas.online.repository.IClienteRepository;

@Service
public class BuscaCliente {
	
	private IClienteRepository clienteRepository;
	
	public BuscaCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Page<Cliente> buscar(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente buscarPorId(String id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Recurso não encontrado para o id informado"));
	}
	
	public Boolean isCadastrado(String id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.isPresent() ? true : false;
	}
	
	public Cliente buscarPorCpf(String cpf) {
		return clienteRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException("Recurso não encontrado para CPF informado"));
	}

}
