package com.elecex.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecex.entities.TransaccionesEntity;
import com.elecex.model.transacciones.TransaccionDto;
import com.elecex.repository.TransaccionesRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TransaccionesServiceImpl{

	@Autowired
	private TransaccionesRepository pransaccionesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void insertarTransaccion (TransaccionDto transaccion) {
		
		log.info("Se procede a insertar una transacción");
		TransaccionesEntity transaccionEntity = modelMapper.map(transaccion, TransaccionesEntity.class);
		
		log.info("Transaccón añadida correctamente {} " + pransaccionesRepository.save(transaccionEntity));
	}
	
}
