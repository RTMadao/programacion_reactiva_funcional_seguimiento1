package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sophossolutions.programacion.reactiva.seguimiento1.dto.DeleteConfirmationDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Pedido;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.OrdenLocalService;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.PedidoKafkaConsumerService;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.PedidoSQSService;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.UtilService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/pedido/local")
public class OrdenLocalController {

    OrdenLocalService ordenLocalService;
    PedidoKafkaConsumerService pedidoKafkaConsumerService;
    PedidoSQSService pedidoSQSService;
    public OrdenLocalController(OrdenLocalService ordenLocalService, PedidoKafkaConsumerService pedidoKafkaConsumerService, PedidoSQSService pedidoSQSService){
        this.ordenLocalService = ordenLocalService;
        this.pedidoKafkaConsumerService = pedidoKafkaConsumerService;
        this.pedidoSQSService = pedidoSQSService;
    }

    @GetMapping("/")
    public Flux<OrdenLocal> getOrdenLocal(){
        return ordenLocalService.getAllOrdenLocal();
    }

    @GetMapping("/{id}")
    public Mono<OrdenLocal> getOrdenLocalById(@PathVariable Integer id){
        return ordenLocalService.getOrdenLocalById(id);
    }

    @PostMapping("/")
    public Mono<OrdenLocal> createOrdenLocal(@RequestBody OrdenLocal ordenLocal){
        return ordenLocalService.createOrdenLocal(ordenLocal);
    }

    @PutMapping("/")
    public Mono<OrdenLocal> updateOrdenLocal(@RequestBody OrdenLocal ordenLocal){
        return ordenLocalService.modificarOrdenLocal(ordenLocal);
    }

    @DeleteMapping("/{id}")
    public Mono<DeleteConfirmationDTO> deleteOrdenLocal(@PathVariable Integer id){
        return ordenLocalService.deleteOrdenLocal(id)
                .map(isSuccessfullyDelete -> DeleteConfirmationDTO.builder()
                        .isSuccessfullyDelete(isSuccessfullyDelete)
                        .message(UtilService.setDeleteMessage(isSuccessfullyDelete))
                        .build());
    }

    @GetMapping("/topico-kakfa/{topico}")
    public Mono<OrdenLocal> getPedidoFromTopicoKafka(@PathVariable String topico) throws JsonProcessingException {
        return Mono.just(pedidoKafkaConsumerService.obtenerUltimoPedido(topico));
    }

    @PostMapping("/aws/createQueue")
    public Mono<String> postCreateQueue(@RequestBody Map<String, Object> requestBody){
        return Mono.just(pedidoSQSService.createStandardQueue((String) requestBody.get("queueName")));
    }

    @PostMapping("/aws/postMessageQueue/{queueName}")
    public Mono<String> postMessageQueue(@RequestBody OrdenLocal ordenLocal, @PathVariable String queueName){
        return Mono.just(pedidoSQSService.publishStandardQueueMessage(
                queueName,
                2,
                ordenLocal));
    }

    @PostMapping("/aws/processPedidoByFecha")
    public Flux<OrdenLocal> deleteOrdenLocalFromQueueByFecha(@RequestBody Map<String, Object> requestBody){
        return pedidoSQSService.deletePedidoMessageInQueue((String) requestBody.get("queueName"),
                (Integer) requestBody.get("maxNumberMessages"),
                (Integer) requestBody.get("waitTimeSeconds"),
                (String) requestBody.get("fechaPedido"))
                .map(OrdenLocal.class::cast);
    }
}
