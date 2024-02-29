package com.elecex.service;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaEstructuraPrincipalDto;

public interface FacturaService {

	public List<FacturaDto> obtenerTodasFacturasProveedor (String proveedor);
	
	public List<FacturaDto> obtenerTodasFacturas();
	
	public List<FacturaEstructuraPrincipalDto> calculoPlazosFacturas ();
	
	public Integer crearFactura (FacturaDto factura);
	
	public FacturaDto pagarFacturaAPlazos (String idFactura, Integer plazosAPagar)throws BadRequestException ;
	
}
