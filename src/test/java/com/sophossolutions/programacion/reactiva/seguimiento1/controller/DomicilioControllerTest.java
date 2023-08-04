package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.dto.DeleteConfirmationDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Domicilio;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Plato;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.PlatoPedido;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.DomicilioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DomicilioControllerTest {

    @InjectMocks
    DomicilioController domicilioController;

    @Mock
    DomicilioService domicilioService;

    @BeforeEach
    public void init() {
        when(domicilioService.getAllDomicilio())
                .thenReturn(Flux.just(getTestDomicilio()));
        when(domicilioService.getDomicilioById(anyInt()))
                .thenReturn(Mono.just(getTestDomicilio()));
        when(domicilioService.createDomicilio(any()))
                .thenReturn(Mono.just(getTestDomicilio()));
        when(domicilioService.modificarDomicilio(any()))
                .thenReturn(Mono.just(getTestDomicilio()));
        when(domicilioService.deleteDomicilio(any()))
                .thenReturn(Mono.just(Boolean.TRUE));
    }

    @Test
    void getDomicilios(){
        Flux<Domicilio> result = domicilioController.getDomicilio();
        StepVerifier.create(result)
                .consumeNextWith(domicilioResult -> assertEquals(getExpectedDomicilio(),domicilioResult))
                .verifyComplete();
    }

    @Test
    void getDomicilioById(){
        Domicilio domicilio = getTestDomicilio();

        Mono<Domicilio> result = domicilioController.getDomicilioById(domicilio.getId());
        StepVerifier.create(result)
                .consumeNextWith(domicilioResult -> assertEquals(getExpectedDomicilio(),domicilioResult))
                .verifyComplete();
    }

    @Test
    void createDomicilio(){
        Domicilio domicilio = getTestDomicilioWithPlatosPedidos();

        Mono<Domicilio> result = domicilioController.createDomicilio(domicilio);
        StepVerifier.create(result)
                .consumeNextWith(domicilioResult -> assertEquals(getExpectedDomicilio(),domicilioResult))
                .verifyComplete();
    }

    @Test
    void updateDomicilio(){
        Domicilio domicilio = getTestDomicilio();

        Mono<Domicilio> result = domicilioController.updateDomicilio(domicilio);
        StepVerifier.create(result)
                .consumeNextWith(domicilioResult -> assertEquals(getExpectedDomicilio(),domicilioResult))
                .verifyComplete();
    }

    @Test
    void deleteDomicilio(){
        Integer domicilioId = 1;

        Mono<DeleteConfirmationDTO> result = domicilioController.deleteDomicilio(domicilioId);
        StepVerifier.create(result)
                .consumeNextWith(confirmationResult -> assertEquals(getDeleteConfirmationDTO(),confirmationResult))
                .verifyComplete();
    }

    private static Domicilio getTestDomicilio(){
        return Domicilio.builder()
                .id(1)
                .fecha(LocalDateTime.of(2023, 7,27,12,0))
                .identificacionCliente("TEL_3125486799")
                .valorDomicilio(6000.00)
                .total(20000.00)
                .build();
    }

    private static Domicilio getTestDomicilioWithPlatosPedidos(){
        List<PlatoPedido> pedidos = new ArrayList<>();
        pedidos.add(PlatoPedido.builder()
                .plato(Plato.builder()
                        .id(1)
                        .nombre("Sopa")
                        .descripcion("Deliciosa sopa de hueso")
                        .precio(7000.00)
                        .build())
                .cantidad(2)
                .build());
        return Domicilio.builder()
                .id(1)
                .fecha(LocalDateTime.of(2023, 7,27,12,0))
                .identificacionCliente("TEL_3125486799")
                .platos(pedidos)
                .valorDomicilio(6000.00)
                .total(20000.00)
                .build();
    }

    private static Domicilio getExpectedDomicilio(){
        return Domicilio.builder()
                .id(1)
                .fecha(LocalDateTime.of(2023, 7,27,12,0))
                .identificacionCliente("TEL_3125486799")
                .valorDomicilio(6000.00)
                .total(20000.00)
                .build();
    }

    private static DeleteConfirmationDTO getDeleteConfirmationDTO(){
        return DeleteConfirmationDTO.builder()
                .isSuccessfullyDelete(Boolean.TRUE)
                .message("Eliminado correctamente")
                .build();
    }
}
