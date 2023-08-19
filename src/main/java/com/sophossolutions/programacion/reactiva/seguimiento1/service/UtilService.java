package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Pedido;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.PlatoPedido;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static Pedido PedidoDeserializer(String pedidoJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(UtilService.setJsonFormat(pedidoJson), OrdenLocal.class);
    }

    public static String setJsonFormat(String json){
        return json.replace("'","\"");
    }

    public static LocalDateTime paseToLocalDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
