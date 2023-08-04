package com.sophossolutions.programacion.reactiva.seguimiento1.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class Cliente implements Persona{
    @Id
    private Integer id;
    private String identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
}
