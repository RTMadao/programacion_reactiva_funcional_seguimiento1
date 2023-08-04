package com.sophossolutions.programacion.reactiva.seguimiento1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Domicilio implements Pedido{
    @Id
    private Integer id;
    private LocalDateTime fecha;
    @Transient
    private List<PlatoPedido> platos;
    private String identificacionCliente;
    private Double valorDomicilio;
    private Double total;

    @Override
    public Mono<Pedido> calcularTotal(Double totalPlatos) {
        return Mono.just(this.toBuilder()
                .total(totalPlatos + this.valorDomicilio)
                .build()
        );
    }
}
