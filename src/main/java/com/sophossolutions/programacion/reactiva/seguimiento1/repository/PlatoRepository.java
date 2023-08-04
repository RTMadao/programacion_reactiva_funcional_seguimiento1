package com.sophossolutions.programacion.reactiva.seguimiento1.repository;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Plato;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends R2dbcRepository<Plato, Integer> {}
