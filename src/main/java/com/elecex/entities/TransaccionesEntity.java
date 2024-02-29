package com.elecex.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "TRANSACCIONES")
@Entity
public class TransaccionesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TRANSACCION")
	private Integer idTransaccion;

	@Column(name = "ID_CLIENTE")
	private String idCliente;

	@Column(name = "ID_PROVEEDOR")
	private String idProveedor;

	@Column(name = "FECHA")
	private LocalDate fecha;

	@Column(name = "TIPO_TRANSACCION")
	private String tipoTransaccion;

	@Column(name = "CATEGORIA")
	private String categoria;
	
	@Column(name = "PLAZOS_RESTANTES")
	private Integer plazosRestante;

	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Column(name = "IMPORTE")
	private BigDecimal importe;

}
