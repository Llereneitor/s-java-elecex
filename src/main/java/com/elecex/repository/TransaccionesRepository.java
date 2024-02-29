package com.elecex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elecex.entities.TransaccionesEntity;

@Repository
public interface TransaccionesRepository  extends JpaRepository<TransaccionesEntity, Integer>{

}
