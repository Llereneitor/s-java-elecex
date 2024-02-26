package com.elecex.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecex.entities.ProveedorEntity;
import com.elecex.model.ProveedorDto;
import com.elecex.repository.ProveedorRepository;
import com.elecex.service.ProveedoresService;

import jakarta.transaction.Transactional;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

	private ProveedorRepository proveedorRepository;

	private ModelMapper modelMapper;

	@Autowired
	public ProveedoresServiceImpl(ProveedorRepository proveedorRepository, ModelMapper modelMapper) {

		this.proveedorRepository = proveedorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public String crearProveedor(ProveedorDto proveedor) {

		ProveedorEntity proveedorEntity = modelMapper.map(proveedor, ProveedorEntity.class);
		proveedorRepository.save(proveedorEntity);
		return "Proveedor guardado correctamente";
	}

	@Transactional
	public String borrarProveedor(String cif) {

		proveedorRepository.deleteById(cif);
		return "El proveedor " + cif + " se ha eliminado correctamente";
	}

	@Override
	public String modificarProveedor(ProveedorDto proveedor) {

		return null;
	}

	@Override
	public ProveedorDto buscarProveedor(String cif) {

		Optional<ProveedorEntity> proveedorEntity = proveedorRepository.findById(cif);

		if (proveedorEntity.isPresent()) {
			ProveedorDto salidaProveedor = modelMapper.map(proveedorEntity.get(), ProveedorDto.class);
			
			return salidaProveedor;
		} else {
			return null;
		}
	}

	@Override
	public List<ProveedorDto> buscarTodosProveedores() {

		List<ProveedorEntity> proveedorEntityList = proveedorRepository.findAll();
		
		if (null != proveedorEntityList) {
			List<ProveedorDto> proveedorDtoList = proveedorEntityList.stream()
	                .map(proveedorEntity -> modelMapper.map(proveedorEntity, ProveedorDto.class))
	                .collect(Collectors.toList());
			return proveedorDtoList;
		}else {
			
			return null;
		}
	}
}
