package com.elecex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elecex.model.cliente.ClienteDto;
import com.elecex.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/obtenerCliente/{cif}")
    public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable String cif) {
    	return new ResponseEntity<>(clienteService.buscarCliente(cif), HttpStatus.OK);
    }
	
	@GetMapping("/obtenerClientes")
    public ResponseEntity<List<ClienteDto>> obtenerTodosClientees() {
    	return new ResponseEntity<>(clienteService.buscarTodosClientes(), HttpStatus.OK);
    }
	
	@DeleteMapping("/borrarCliente/{cif}")
	public ResponseEntity<String> borrarCliente(@PathVariable String cif) {
		
		return new ResponseEntity<>(clienteService.borrarCliente(cif), HttpStatus.OK);
	}
	
	@PostMapping("/crearCliente")
    public ResponseEntity<String> CrearCliente(@RequestBody ClienteDto ClienteInputDto) {
    	return new ResponseEntity<String>(clienteService.crearCliente(ClienteInputDto), HttpStatus.OK);
    }
	
	@PatchMapping("/modificarCliente")
    public ResponseEntity<String> modificarCliente(@RequestBody ClienteDto ClienteInputDto) {
    	return new ResponseEntity<String>(clienteService.modificarCliente(ClienteInputDto), HttpStatus.OK);
    }
}
