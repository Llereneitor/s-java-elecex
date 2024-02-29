package com.elecex.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaOutputDto;
import com.elecex.model.proveedor.ProveedorDto;

public interface ProveedoresService {

	public String crearProveedor(ProveedorDto proveedor);
	
	public String borrarProveedor(String cif);
	
	public String modificarProveedor(ProveedorDto proveedor);
	
	public ProveedorDto buscarProveedor(String cif);
	
	public List<ProveedorDto> buscarTodosProveedores();
	
}
