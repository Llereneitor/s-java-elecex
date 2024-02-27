package com.elecex.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecex.entities.ProveedorEntity;
import com.elecex.model.ProveedorDto;
import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaOutputDto;
import com.elecex.repository.ProveedorRepository;
import com.elecex.service.ProveedoresService;
import com.elecex.utils.Utils;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProveedoresServiceImpl implements ProveedoresService {

	private ProveedorRepository proveedorRepository;

	private Utils utils;

	private ModelMapper modelMapper;

	@Autowired
	public ProveedoresServiceImpl(ProveedorRepository proveedorRepository, ModelMapper modelMapper, Utils utils) {

		this.proveedorRepository = proveedorRepository;
		this.modelMapper = modelMapper;
		this.utils = utils;
	}

	@Override
	public String crearProveedor(ProveedorDto proveedor) {

		ProveedorEntity proveedorEntity = modelMapper.map(proveedor, ProveedorEntity.class);
		proveedorRepository.save(proveedorEntity);
		return "Proveedor con cif " + proveedor.getCif() + " se ha guardado correctamente";
	}

	@Transactional
	public String borrarProveedor(String cif) {

		proveedorRepository.deleteById(cif);
		return "El proveedor " + cif + " se ha eliminado correctamente";
	}

	@Override
	public String modificarProveedor(ProveedorDto proveedor) {

		ProveedorDto proveedorBbdd = buscarProveedor(proveedor.getCif());

		if (null != proveedor.getNombre() && !proveedor.getNombre().equals(proveedorBbdd.getNombre())) {
			proveedorBbdd.setNombre(proveedor.getNombre());
		}
		if (null != proveedor.getApellido1() && !proveedor.getApellido1().equals(proveedorBbdd.getApellido1())) {
			proveedorBbdd.setApellido1(proveedor.getApellido1());
		}
		if (null != proveedor.getApellido2() && !proveedor.getApellido2().equals(proveedorBbdd.getApellido2())) {
			proveedorBbdd.setApellido2(proveedor.getApellido2());
		}
		if (null != proveedor.getDireccion() && !proveedor.getDireccion().equals(proveedorBbdd.getDireccion())) {
			proveedorBbdd.setDireccion(proveedor.getDireccion());
		}
		if (null != proveedor.getEmail() && !proveedor.getEmail().equals(proveedorBbdd.getEmail())) {
			proveedorBbdd.setEmail(proveedor.getEmail());
		}
		if (null != proveedor.getTelefono() && !proveedor.getTelefono().equals(proveedorBbdd.getTelefono())) {
			proveedorBbdd.setTelefono(proveedor.getTelefono());
		}

		ProveedorEntity proveedorEntity = modelMapper.map(proveedorBbdd, ProveedorEntity.class);

		proveedorRepository.save(proveedorEntity);

		return "El proveedor se ha modificado correctamente.";
	}

	@Override
	public ProveedorDto buscarProveedor(String cif) {

		Optional<ProveedorEntity> proveedorEntity = proveedorRepository.findById(cif);

		if (proveedorEntity.isPresent()) {
			ProveedorDto salidaProveedor = modelMapper.map(proveedorEntity.get(), ProveedorDto.class);

			return salidaProveedor;
		} else {
			throw new IllegalArgumentException("El usuario no existe en la base de datos.");
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
		} else {
			throw new IllegalArgumentException("El usuario no existe en la base de datos.");
		}
	}

	@Override
	public FacturaDto leerFacturaPorImagen(MultipartFile pdf) {

		// Fumada maxima, no se si es viable hacerlo de esta forma, ya lo hablaremos
		FacturaDto resultadoFactura = utils.anotherWay(pdf);
		return resultadoFactura;
	}

	
}
