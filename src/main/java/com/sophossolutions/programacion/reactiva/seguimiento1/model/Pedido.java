package com.sophossolutions.programacion.reactiva.seguimiento1.model;

import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public interface Pedido {
    Mono<Pedido> calcularTotal(Double totalPlatos);

    default Mono<Double> calcularTotalPlatos(Stream<PlatoPedido> pedidoStream){
        return Mono.just(pedidoStream.mapToDouble(pedido -> pedido.getPlato().getPrecio() * pedido.getCantidad()).sum());
    }
}
