package com.elecex.service;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.elecex.model.cliente.ClienteDto;

public interface ClienteService {

	public String crearCliente(ClienteDto Cliente) throws BadRequestException;

	public String borrarCliente(String cif) throws BadRequestException;

	public String modificarCliente(ClienteDto Cliente);

	public ClienteDto buscarCliente(String cif);

	public List<ClienteDto> buscarTodosClientes();
}
