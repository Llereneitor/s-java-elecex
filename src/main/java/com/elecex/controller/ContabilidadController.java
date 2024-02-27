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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elecex.model.ProveedorDto;
import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaOutputDto;
import com.elecex.service.ProveedoresService;

@RestController
@RequestMapping("/contabilidad")
public class ContabilidadController {

	ProveedoresService proveedoresService;
	
	@Autowired
	public ContabilidadController (ProveedoresService proveedoresService) {
		
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
	
	@PostMapping("/leerFacturas")
	public ResponseEntity<FacturaDto> leerFacturas(@RequestParam("file") MultipartFile file){
		
		return new ResponseEntity<FacturaDto>(proveedoresService.leerFacturaPorImagen(file), HttpStatus.OK);
	}
}
