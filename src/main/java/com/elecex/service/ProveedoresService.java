package com.elecex.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.elecex.model.ProveedorDto;
import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaOutputDto;

public interface ProveedoresService {

	public String crearProveedor(ProveedorDto proveedor);
	
	public String borrarProveedor(String cif);
	
	public String modificarProveedor(ProveedorDto proveedor);
	
	public ProveedorDto buscarProveedor(String cif);
	
	public List<ProveedorDto> buscarTodosProveedores();
	
	public FacturaDto leerFacturaPorImagen(MultipartFile pdf);
	
}
