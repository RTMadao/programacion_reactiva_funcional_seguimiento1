package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sophossolutions.programacion.reactiva.seguimiento1.config.KafkaConfig;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PedidoKafkaConsumerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PedidoKafkaConsumerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrdenLocal obtenerUltimoPedido(String topico) throws JsonProcessingException {
        ConsumerRecord<String, String> ultimoPedido;
        KafkaConfig kafkaConfig = new KafkaConfig();
        kafkaTemplate.setConsumerFactory(kafkaConfig.consumerFactory());
        ultimoPedido = kafkaTemplate.receive(topico, 0, 0);


        return (OrdenLocal) UtilService.PedidoDeserializer(Objects.requireNonNull(ultimoPedido.value()));
    }
}
