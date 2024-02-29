package com.elecex.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
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
	
	public FacturaController() {
	}

	@Autowired
	public FacturaController(FacturaService facturaService) {

		this.facturaService = facturaService;
	}

	@GetMapping("/obtenerFacturasProveedor")
	public ResponseEntity<List<FacturaDto>> obtenerFacturasProveedor(@RequestParam String proveedor) {

		return new ResponseEntity<>(facturaService.obtenerTodasFacturasProveedor(proveedor), HttpStatus.OK);
	}

	@GetMapping("/obtenerTodasFacturas")
	public ResponseEntity<List<FacturaDto>> obtenerTodasFacturasImpagadas() {

		return new ResponseEntity<>(facturaService.obtenerTodasFacturas(), HttpStatus.OK);
	}

	@PostMapping("/crearFactura")
	public ResponseEntity<Integer> crearFactura(@RequestBody FacturaDto factura) {

		return new ResponseEntity<Integer>(facturaService.crearFactura(factura), HttpStatus.OK);
	}

	@PostMapping("/facturaAPlazosImpagadas")
	public ResponseEntity<List<FacturaEstructuraPrincipalDto>> facturaAPlazos() {

		return new ResponseEntity<>(facturaService.calculoPlazosFacturas(), HttpStatus.OK);
	}

	@PostMapping("/pagarFacturaAPlazos")
	public ResponseEntity<FacturaDto> pagarFacturaAPlazos(@RequestParam String idFactura,
			@RequestParam Integer plazosAPagar) throws BadRequestException {

		return new ResponseEntity<FacturaDto>(facturaService.pagarFacturaAPlazos(idFactura, plazosAPagar),
				HttpStatus.OK);
	}

}
