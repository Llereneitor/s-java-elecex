package com.elecex.service;

import java.util.List;

import com.elecex.model.cliente.ClienteDto;


public interface ClienteService {

public String crearCliente(ClienteDto Cliente);
	
	public String borrarCliente(String cif);
	
	public String modificarCliente(ClienteDto Cliente);
	
	public ClienteDto buscarCliente(String cif);
	
	public List<ClienteDto> buscarTodosClientes();
}
