package com.sophossolutions.programacion.reactiva.seguimiento1.repository;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenLocalRepository extends R2dbcRepository<OrdenLocal, Integer> {}
