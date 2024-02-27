package com.elecex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaEstructuraPrincipalDto;
import com.elecex.service.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

	FacturaService facturaService;
	
	@Autowired
	public FacturaController (FacturaService facturaService) {
		
		this.facturaService = facturaService;
	}
	
	@GetMapping("/obtenerFacturasProveedor")
	public ResponseEntity<List<FacturaDto>> obtenerFacturasProveedor(@RequestParam String proveedor){
		
		return new ResponseEntity<>(facturaService.obtenerTodasFacturas(proveedor), HttpStatus.OK);
	}
	
	@GetMapping("/obtenerTodasFacturasImpagadas")
	public ResponseEntity<List<FacturaDto>> obtenerTodasFacturasImpagadas(){
		
		return new ResponseEntity<>(facturaService.obtenerTodasFacturasImpagadas(), HttpStatus.OK);
	}
	
	@PostMapping("/crearFactura")
	public ResponseEntity<Integer>crearFactura (@RequestBody FacturaDto factura){
		
		return new ResponseEntity<Integer>(facturaService.crearFactura(factura), HttpStatus.OK);
	}
	
	@PostMapping("/facturaAPlazos")
	public ResponseEntity<List<FacturaEstructuraPrincipalDto>> facturaAPlazos(@RequestBody List<FacturaDto> factura){
		
		return new ResponseEntity<>(facturaService.calculoPlazosFacturas(factura), HttpStatus.OK);
	}
}
