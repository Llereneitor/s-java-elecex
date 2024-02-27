package com.elecex.model.facturas;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaEstructuraPrincipalDto {

	private String idFactura;
	
	private String proveedor;
	
	private List<FacturaOutputDto> facturaOutputDto;
}
