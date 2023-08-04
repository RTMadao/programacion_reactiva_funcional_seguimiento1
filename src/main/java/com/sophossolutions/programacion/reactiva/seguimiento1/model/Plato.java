package com.sophossolutions.programacion.reactiva.seguimiento1.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class Plato {
    @Id
    protected Integer id;
    protected String nombre;
    protected String descripcion;
    protected Double precio;
}
