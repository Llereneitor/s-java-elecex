package com.elecex.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FACTURAS")
@Entity
public class FacturaEntity {

	@Id
	@Column(name = "ID_FACTURA")
	private String idFactura;
	
	@Column(name = "PROVEEDOR")
	private String proveedor;
	
	@Column(name = "FECHA_REALIZADA")
	private LocalDate fecha;
	
	@Column(name = "IMPORTE")
	private BigDecimal importe;
	
	@Column(name = "TIEMPO_ENTRE_PLAZOS")
	private Integer tiempoEntrePlazos;
	
	@Column(name = "NUMERO_PLAZOS")
	private Integer numPlazosAPagar;
}
