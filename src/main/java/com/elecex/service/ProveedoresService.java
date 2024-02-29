package com.elecex.service;

import java.util.List;

import com.elecex.model.proveedor.ProveedorDto;

public interface ProveedoresService {

	public String crearProveedor(ProveedorDto proveedor);
	
	public String borrarProveedor(String cif);
	
	public String modificarProveedor(ProveedorDto proveedor);
	
	public ProveedorDto buscarProveedor(String cif);
	
	public List<ProveedorDto> buscarTodosProveedores();
	
}
