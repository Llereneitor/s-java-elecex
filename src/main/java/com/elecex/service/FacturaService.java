package com.elecex.service;

import java.util.List;

import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaEstructuraPrincipalDto;

public interface FacturaService {

	public List<FacturaDto> obtenerTodasFacturas (String proveedor);
	
	public List<FacturaDto> obtenerTodasFacturasImpagadas();
	
	public List<FacturaEstructuraPrincipalDto> calculoPlazosFacturas (List<FacturaDto> factura);
	
	public Integer crearFactura (FacturaDto factura);
	
	
}
