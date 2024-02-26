package com.elecex.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDto {

	private String cif; 
	private String nombre; 
	private String apellido1; 
	private String apellido2; 
	private String direccion;
	private String email; 
	private String telefono;
}
