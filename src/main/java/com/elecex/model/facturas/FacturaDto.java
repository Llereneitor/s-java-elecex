package com.elecex.model.facturas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDto {

	private String idFactura;
	
	@JsonProperty("proveedor")
	private String proveedor;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha;

	private BigDecimal importe;
	
	private Integer tiempoEntrePlazos;
	
	private Integer numPlazosAPagar;
	
}
