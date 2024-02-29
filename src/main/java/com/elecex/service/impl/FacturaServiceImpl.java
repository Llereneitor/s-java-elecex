package com.elecex.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecex.entities.FacturaEntity;
import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaEstructuraPrincipalDto;
import com.elecex.model.facturas.FacturaOutputDto;
import com.elecex.model.transacciones.TransaccionDto;
import com.elecex.repository.FacturasRepository;
import com.elecex.service.FacturaService;
import com.elecex.utils.Constantes;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FacturaServiceImpl implements FacturaService {

	private FacturasRepository facturasRepository;

	private ModelMapper modelMapper;
	
	private TransaccionesServiceImpl transacciones;

	@Autowired
	public FacturaServiceImpl(FacturasRepository facturasRepository, ModelMapper modelMapper,
			TransaccionesServiceImpl transacciones) {

		this.facturasRepository = facturasRepository;
		this.modelMapper = modelMapper;
		this.transacciones = transacciones;
	}

	@Override
	public List<FacturaDto> obtenerTodasFacturasProveedor(String proveedor) {

		List<FacturaEntity> facturaEntity = facturasRepository.findAllByProveedor(proveedor);

		List<FacturaDto> facturaDtoList = facturaEntity.stream()
				.map(facturas -> modelMapper.map(facturas, FacturaDto.class)).collect(Collectors.toList());

		return facturaDtoList;
	}

	@Override
	public List<FacturaDto> obtenerTodasFacturas() {
		
		List<FacturaEntity> facturaEntity = facturasRepository.findAll();
		
		List<FacturaDto> facturaOutput = facturaEntity.stream()
				.map(facturas -> modelMapper.map(facturas, FacturaDto.class))
				.collect(Collectors.toList());
		
		return facturaOutput;
	}

	@Override
	public Integer crearFactura(FacturaDto factura) {

		FacturaEntity facturaEntity = modelMapper.map(factura, FacturaEntity.class);

		if (null != facturaEntity)
			facturasRepository.save(facturaEntity);
		else
			return 0;

		//AÑADIR LOGICA SI PAGOS ES 0 PAGAR DIRECTAMENTE.
		
		añadirTransaccion(factura, Constantes.CATEGORIA_REGISTRO, factura.getNumPlazosAPagar());
		
		
		return 1;
	}

	public List<FacturaEstructuraPrincipalDto> calculoPlazosFacturas() {

		List<FacturaEstructuraPrincipalDto> facturaOutput = new ArrayList<>();

		List<FacturaDto> facturaInput = obtenerTodasFacturas();
		
		facturaInput.forEach(factura -> {
			
			if (!factura.getNumPlazosAPagar().equals(0)) {
				
				
				BigDecimal importePorMes = factura.getImporte().divide(new BigDecimal(factura.getNumPlazosAPagar()),
						RoundingMode.HALF_UP);
				LocalDate fecha = factura.getFecha();

				List<FacturaOutputDto> facturaFechaImporte = new ArrayList<>();

				Integer longitudPlazos = 1;
				
				if (0 != factura.getTiempoEntrePlazos())
					longitudPlazos = factura.getNumPlazosAPagar();
					
					
				for (int i = 0; i < longitudPlazos; i++) {

					fecha = fecha.plusMonths(factura.getTiempoEntrePlazos());
					FacturaOutputDto f = new FacturaOutputDto(fecha, importePorMes);
					facturaFechaImporte.add(f);
				}
				String idFactura = factura.getIdFactura();
				String proveedor = factura.getProveedor();

				facturaOutput.add(new FacturaEstructuraPrincipalDto(idFactura, proveedor, facturaFechaImporte));
			}

		});

		return facturaOutput;
	}

	@Override
	public FacturaDto pagarFacturaAPlazos(String idFactura, Integer plazosAPagar) throws BadRequestException {
		
		Optional<FacturaEntity> facturaEntity = facturasRepository.findById(idFactura);
		
		if (!facturaEntity.isPresent()) {
			throw new BadRequestException("La factura: " + idFactura + " no es correcta");
		}
		Integer numPlazosRestantes = facturaEntity.get().getNumPlazosAPagar();
		
		if(numPlazosRestantes == 0) {
			throw new BadRequestException("La factura: " + idFactura + " Ya está pagada");
		}
		
		if(plazosAPagar > numPlazosRestantes) {
			
			throw new BadRequestException("La factura: " + idFactura + " No tiene tantos plazos por pagar, queda: " + numPlazosRestantes);
		}
		Integer numPlazosQuedan = numPlazosRestantes-plazosAPagar;
		
		facturaEntity.get().setNumPlazosAPagar(numPlazosQuedan);
		
		facturasRepository.save(facturaEntity.get());
		
		FacturaDto facturaDto =  modelMapper.map(facturaEntity.get(), FacturaDto.class);
		
		añadirTransaccion(facturaDto, Constantes.CATEGORIA_PAGO, numPlazosQuedan);
		log.info("Se registra transaccion", Constantes.CATEGORIA_PAGO );
		
		return facturaDto;
	}
	
	private void añadirTransaccion (FacturaDto factura, String categoria, Integer plazosRestante) {
		String estado;
		if (plazosRestante.equals(0)) {
			estado = Constantes.ESTADO_PAGADO;
		}else {
			estado = Constantes.ESTADO_PENDIENTE;
		}
		
		var fechaActual = LocalDate.now();
		
		TransaccionDto transaccion = TransaccionDto.builder()
				.idProveedor(factura.getProveedor())
				.fecha(fechaActual)
				.tipoTransaccion(Constantes.COMPRA)
				.categoria(categoria)
				.plazosRestante(plazosRestante)
				.estado(estado)
				.descripcion("Factura del proveedor {" + factura.getProveedor() + "} Con importe " + factura.getImporte())
				.importe(factura.getImporte())
				.build();
		
		transacciones.insertarTransaccion(transaccion);
		
	}

}
