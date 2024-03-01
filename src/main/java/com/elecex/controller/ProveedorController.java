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

import com.elecex.model.proveedor.ProveedorDto;
import com.elecex.service.ProveedoresService;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

	ProveedoresService proveedoresService;
	
	public ProveedorController () {}
	
	@Autowired
	public ProveedorController (ProveedoresService proveedoresService) {
		
		this.proveedoresService = proveedoresService;
	}
	
	@GetMapping("/obtenerProveedor/{cif}")
    public ResponseEntity<ProveedorDto> obtenerProveedor(@PathVariable String cif) {
    	return new ResponseEntity<>(proveedoresService.buscarProveedor(cif), HttpStatus.OK);
    }
	
	@GetMapping("/obtenerProveedor")
    public ResponseEntity<List<ProveedorDto>> obtenerTodosProveedores() {
    	return new ResponseEntity<>(proveedoresService.buscarTodosProveedores(), HttpStatus.OK);
    }
	
	@DeleteMapping("/borrarProveedor/{cif}")
	public ResponseEntity<String> borrarProveedor(@PathVariable String cif) {
		
		return new ResponseEntity<>(proveedoresService.borrarProveedor(cif), HttpStatus.OK);
	}
	
	@PostMapping("/crearProveedor")
    public ResponseEntity<String> CrearProveedor(@RequestBody ProveedorDto proveedorInputDto) {
    	return new ResponseEntity<String>(proveedoresService.crearProveedor(proveedorInputDto), HttpStatus.OK);
    }
	
	@PatchMapping("/modificarProveedor")
    public ResponseEntity<String> modificarProveedor(@RequestBody ProveedorDto proveedorInputDto) {
    	return new ResponseEntity<String>(proveedoresService.modificarProveedor(proveedorInputDto), HttpStatus.OK);
    }
}
