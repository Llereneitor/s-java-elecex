package com.elecex.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecex.entities.ClienteEntity;
import com.elecex.model.cliente.ClienteDto;
import com.elecex.model.transacciones.TransaccionDto;
import com.elecex.repository.ClienteRepository;
import com.elecex.repository.FacturasRepository;
import com.elecex.service.ClienteService;
import com.elecex.utils.Constantes;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FacturasRepository facturasRepository;
	
	@Autowired
	private TransaccionesServiceImpl transacciones;
	
	@Override
	public String crearCliente(ClienteDto cliente) throws BadRequestException {

		if(clienteRepository.findById(cliente.getCif()).isPresent()) {
			throw new BadRequestException("Cliente ya registrado");
		}
		ClienteEntity clienteEntity = modelMapper.map(cliente, ClienteEntity.class);
		clienteRepository.save(clienteEntity);
		
		añadirTransaccion(cliente, Constantes.ALTA);
		
		return "Cliente con cif " + cliente.getCif() + " se ha guardado correctamente";
	}

	@Transactional
	public String borrarCliente(String cif) throws BadRequestException{
		
		if(false == clienteRepository.findByCif(cif)) {
			throw new BadRequestException("Cliente no registrado");
		}
		
		clienteRepository.deleteById(cif);
		return "El Cliente " + cif + " se ha eliminado correctamente";
	}

	@Override
	public String modificarCliente(ClienteDto cliente) {

		ClienteDto clienteBbdd = buscarCliente(cliente.getCif());

		if (null != cliente.getNombre() && !cliente.getNombre().equals(clienteBbdd.getNombre())) {
			clienteBbdd.setNombre(cliente.getNombre());
		}
		if (null != cliente.getApellido1() && !cliente.getApellido1().equals(clienteBbdd.getApellido1())) {
			clienteBbdd.setApellido1(cliente.getApellido1());
		}
		if (null != cliente.getApellido2() && !cliente.getApellido2().equals(clienteBbdd.getApellido2())) {
			clienteBbdd.setApellido2(cliente.getApellido2());
		}
		if (null != cliente.getDireccion() && !cliente.getDireccion().equals(clienteBbdd.getDireccion())) {
			clienteBbdd.setDireccion(cliente.getDireccion());
		}
		if (null != cliente.getEmail() && !cliente.getEmail().equals(clienteBbdd.getEmail())) {
			clienteBbdd.setEmail(cliente.getEmail());
		}
		if (null != cliente.getTelefono() && !cliente.getTelefono().equals(clienteBbdd.getTelefono())) {
			clienteBbdd.setTelefono(cliente.getTelefono());
		}

		ClienteEntity clienteEntity = modelMapper.map(clienteBbdd, ClienteEntity.class);

		clienteRepository.save(clienteEntity);

		return "El Cliente se ha modificado correctamente.";
	}

	@Override
	public ClienteDto buscarCliente(String cif) {

		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(cif);

		if (clienteEntity.isPresent()) {
			ClienteDto salidaCliente = modelMapper.map(clienteEntity.get(), ClienteDto.class);

			return salidaCliente;
		} else {
			throw new IllegalArgumentException("El usuario no existe en la base de datos.");
		}

	}

	@Override
	public List<ClienteDto> buscarTodosClientes() {

		List<ClienteEntity> clienteEntityList = clienteRepository.findAll();

		if (null != clienteEntityList) {
			List<ClienteDto> clienteDtoList = clienteEntityList.stream()
					.map(cliente -> modelMapper.map(cliente, ClienteDto.class))
					.collect(Collectors.toList());
			return clienteDtoList;
		} else {
			throw new IllegalArgumentException("El usuario no existe en la base de datos.");
		}
	}
	
	private void añadirTransaccion (ClienteDto cliente, String tipoTransaccion) {
		var fechaActual = LocalDate.now();
		
		TransaccionDto transaccion = TransaccionDto.builder()
				.idCliente(cliente.getNombre())
				.idProveedor(null)
				.fecha(fechaActual)
				.tipoTransaccion(tipoTransaccion)
				.categoria(null)
				.plazosRestante(null)
				.estado(null)
				.descripcion("Alta del cliente" + cliente.getNombre())
				.importe(null)
				.build();
		
		transacciones.insertarTransaccion(transaccion);
		
	}

}
