package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Pedido;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.PlatoPedido;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Service
public class UtilService {
    private static final String  SUCCESSFULLY_DELETED_MESSAGE = "Eliminado correctamente";
    private static final String  FAILED_DELETE_MESSAGE = "Ocurrio un error al eliminar el item";
    public static Mono<Pedido> calcularTotalPedido(Pedido pedido, Stream<PlatoPedido> pedidosStream){
        return pedido.calcularTotalPlatos(pedidosStream)
                .flatMap(pedido::calcularTotal);
    }

    public static String setDeleteMessage(Boolean isSuccessfullyDelete){
        return (isSuccessfullyDelete)? SUCCESSFULLY_DELETED_MESSAGE : FAILED_DELETE_MESSAGE;
    }
}
