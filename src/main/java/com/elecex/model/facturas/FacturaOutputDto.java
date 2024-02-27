package com.elecex.model.facturas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaOutputDto {

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha;
	
	private BigDecimal importe;
}
