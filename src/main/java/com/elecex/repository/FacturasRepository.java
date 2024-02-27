package com.elecex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elecex.entities.FacturaEntity;
import java.util.List;


@Repository
public interface FacturasRepository extends JpaRepository<FacturaEntity, String>{

	List<FacturaEntity> findAllByProveedor(String proveedor);
}
