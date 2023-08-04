package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.dto.DeleteConfirmationDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Domicilio;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Plato;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.PlatoPedido;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.OrdenLocalService;
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
class OrdenLocalControllerTest {

    @InjectMocks
    OrdenLocalController ordenLocalController;

    @Mock
    OrdenLocalService ordenLocalService;

    @BeforeEach
    public void init() {
        when(ordenLocalService.getAllOrdenLocal())
                .thenReturn(Flux.just(getTestOrdenLocal()));
        when(ordenLocalService.getOrdenLocalById(anyInt()))
                .thenReturn(Mono.just(getTestOrdenLocal()));
        when(ordenLocalService.createOrdenLocal(any()))
                .thenReturn(Mono.just(getTestOrdenLocal()));
        when(ordenLocalService.modificarOrdenLocal(any()))
                .thenReturn(Mono.just(getTestOrdenLocal()));
        when(ordenLocalService.deleteOrdenLocal(any()))
                .thenReturn(Mono.just(Boolean.TRUE));
    }

    @Test
    void getOrdenLocals(){
        Flux<OrdenLocal> result = ordenLocalController.getOrdenLocal();
        StepVerifier.create(result)
                .consumeNextWith(ordenLocalResult -> assertEquals(getExpectedOrdenLocal(),ordenLocalResult))
                .verifyComplete();
    }

    @Test
    void getOrdenLocalById(){
        OrdenLocal ordenLocal = getTestOrdenLocal();

        Mono<OrdenLocal> result = ordenLocalController.getOrdenLocalById(ordenLocal.getId());
        StepVerifier.create(result)
                .consumeNextWith(ordenLocalResult -> assertEquals(getExpectedOrdenLocal(),ordenLocalResult))
                .verifyComplete();
    }

    @Test
    void createOrdenLocal(){
        OrdenLocal ordenLocal = getTestOrdenLocalWithPlatosPedidos();

        Mono<OrdenLocal> result = ordenLocalController.createOrdenLocal(ordenLocal);
        StepVerifier.create(result)
                .consumeNextWith(ordenLocalResult -> assertEquals(getExpectedOrdenLocal(),ordenLocalResult))
                .verifyComplete();
    }

    @Test
    void updateOrdenLocal(){
        OrdenLocal ordenLocal = getExpectedOrdenLocal();

        Mono<OrdenLocal> result = ordenLocalController.updateOrdenLocal(ordenLocal);
        StepVerifier.create(result)
                .consumeNextWith(ordenLocalResult -> assertEquals(getExpectedOrdenLocal(),ordenLocalResult))
                .verifyComplete();
    }

    @Test
    void deleteOrdenLocal(){
        Integer ordenLocalId = 1;

        Mono<DeleteConfirmationDTO> result = ordenLocalController.deleteOrdenLocal(ordenLocalId);
        StepVerifier.create(result)
                .consumeNextWith(confirmationResult -> assertEquals(getDeleteConfirmationDTO(),confirmationResult))
                .verifyComplete();
    }

    private static OrdenLocal getTestOrdenLocal(){
        return OrdenLocal.builder()
                .id(1)
                .fecha(LocalDateTime.of(2023, 7,27,12,0))
                .total(20000.00)
                .build();
    }

    private static OrdenLocal getTestOrdenLocalWithPlatosPedidos(){
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
        return OrdenLocal.builder()
                .id(1)
                .fecha(LocalDateTime.of(2023, 7,27,12,0))
                .platos(pedidos)
                .total(20000.00)
                .build();
    }

    private static OrdenLocal getExpectedOrdenLocal(){
        return OrdenLocal.builder()
                .id(1)
                .fecha(LocalDateTime.of(2023, 7,27,12,0))
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
