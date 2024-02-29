package com.elecex.model.transacciones;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaccionDto {

	private Integer idTransaccion;
	
	private String idCliente;
	
	private String idProveedor;
	
	private LocalDate fecha;
	
	//COMPRA(SI ES UNA FACTURA DE UN PROVEEDOR) - VENTA(SI SE EMITE FACTURA A CLIENTE) -...
	private String tipoTransaccion;
	
	//Indica si se paga factura, si se crea...
	private String categoria;
	
	//IF 0 == PAGADA ELSE PENDIENTE X PLAZOS
	private Integer plazosRestante;
	
	private String estado;
	
	private String descripcion;
	
	private BigDecimal importe;
	
}
