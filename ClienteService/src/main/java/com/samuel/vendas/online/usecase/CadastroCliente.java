package com.samuel.vendas.online.usecase;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.samuel.vendas.online.domain.Cliente;
import com.samuel.vendas.online.repository.IClienteRepository;

@Service
public class CadastroCliente {

	private IClienteRepository clienteRepository;

	public CadastroCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente cadastrar(@Valid Cliente cliente) {
		return clienteRepository.insert(cliente);
	}
	
	public  Cliente atualizar(@Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void remover(String id) {
		clienteRepository.deleteById(id);
	}
}
