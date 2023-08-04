package com.sophossolutions.programacion.reactiva.seguimiento1.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class PlatoPedido{
    @Id
    private Integer id;
    private Plato plato;
    private Integer cantidad;
}
