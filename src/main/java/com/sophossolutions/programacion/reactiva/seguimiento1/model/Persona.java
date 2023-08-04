package com.sophossolutions.programacion.reactiva.seguimiento1.model;

public interface Persona {
    default String mapIdentificacion(String tipoIdentificacion, String identificacion){
        return tipoIdentificacion.concat("_").concat(identificacion);
    }
}
