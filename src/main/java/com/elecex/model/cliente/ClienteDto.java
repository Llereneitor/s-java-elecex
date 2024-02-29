package com.elecex.model.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

	private String cif;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private String direccion;

	private String telefono;

	private String email;
}
