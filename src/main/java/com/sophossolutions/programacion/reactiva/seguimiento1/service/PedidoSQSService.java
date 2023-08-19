package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Pedido;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoSQSService {
    private final AmazonSQS clienteSQS;

    public PedidoSQSService(AmazonSQS clienteSQS) {
        this.clienteSQS = clienteSQS;
    }

    public String createStandardQueue(String queueName){
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return clienteSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    private String getQueueUrl(String queueName){
        return clienteSQS.getQueueUrl(queueName).getQueueUrl();
    }

    public String publishStandardQueueMessage(String queueName, Integer delaySeconds, OrdenLocal ordenLocal){
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();

        atributosMensaje.put("id",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(ordenLocal.getId()).orElse(-301).toString())
                        .withDataType("Number"));
        atributosMensaje.put("fecha",
                new MessageAttributeValue()
                        .withStringValue(ordenLocal.getFecha().toString())
                        .withDataType("String"));
        atributosMensaje.put("total",
                new MessageAttributeValue()
                        .withStringValue(ordenLocal.getTotal().toString())
                        .withDataType("String"));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl(queueName))
                .withMessageBody(ordenLocal.getFecha().toString())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return clienteSQS.sendMessage(sendMessageRequest).getMessageId();
    }

    public void publishStandardQueueMessage(String queueName, Integer delaySeconds, List<OrdenLocal> ordenLocalList){
        for (OrdenLocal ordenLocal : ordenLocalList){
            publishStandardQueueMessage(queueName, delaySeconds, ordenLocal);
        }
    }

    private List<Message> receiveMessagesFromQueue(String queueName, Integer maxNumberMessages, Integer waitTimeSeconds){
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(this.getQueueUrl(queueName))
                .withMaxNumberOfMessages(maxNumberMessages)
                .withMessageAttributeNames(List.of("All"))
                .withWaitTimeSeconds(waitTimeSeconds);
        return clienteSQS.receiveMessage(receiveMessageRequest).getMessages();
    }

    public Flux<Pedido> deletePedidoMessageInQueue(String queueName, Integer maxNumberMessages,
                                                    Integer waitTimeSeconds, String fechaPedido){
        List<Message> pedidoMessages = receiveMessagesFromQueue(queueName, maxNumberMessages, waitTimeSeconds);
        return Flux.fromIterable(pedidoMessages)
                .filter(message -> message.getMessageAttributes().get("fecha").getStringValue().equals(fechaPedido))
                .map(message -> this.buildMessageFromQueue(message,queueName));
    }
    public Pedido buildMessageFromQueue(Message message, String queueName){
        OrdenLocal ordenLocal = OrdenLocal.builder()
                .id(Integer.valueOf(message.getMessageAttributes().get("id").getStringValue()))
                .fecha(UtilService.paseToLocalDateTime(message.getMessageAttributes().get("fecha").getStringValue()))
                .total(Double.valueOf(message.getMessageAttributes().get("total").getStringValue()))
                .build();
        clienteSQS.deleteMessage(this.getQueueUrl(queueName), message.getReceiptHandle());
        return ordenLocal;
    }
}
