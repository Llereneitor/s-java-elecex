package com.elecex.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elecex.entities.FacturaEntity;
import com.elecex.model.facturas.FacturaDto;
import com.elecex.model.facturas.FacturaEstructuraPrincipalDto;
import com.elecex.model.facturas.FacturaOutputDto;
import com.elecex.repository.FacturasRepository;
import com.elecex.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	private FacturasRepository facturasRepository;

	private ModelMapper modelMapper;

	@Autowired
	public FacturaServiceImpl(FacturasRepository facturasRepository, ModelMapper modelMapper) {

		this.facturasRepository = facturasRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<FacturaDto> obtenerTodasFacturas(String proveedor) {

		List<FacturaEntity> facturaEntity = facturasRepository.findAllByProveedor(proveedor);

		List<FacturaDto> facturaDtoList = facturaEntity.stream()
				.map(facturas -> modelMapper.map(facturas, FacturaDto.class)).collect(Collectors.toList());

		return facturaDtoList;
	}

	@Override
	public List<FacturaDto> obtenerTodasFacturasImpagadas() {
		
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

		return 1;
	}

	public List<FacturaEstructuraPrincipalDto> calculoPlazosFacturas(List<FacturaDto> facturaInput) {

		List<FacturaEstructuraPrincipalDto> facturaOutput = new ArrayList<>();

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

}
