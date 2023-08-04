package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Cliente;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.ClienteService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    ClienteService clienteService;

    @BeforeEach
    public void init() {
        when(clienteService.getAllClientes())
                .thenReturn(Flux.just(getTestClient()));
        when(clienteService.getClienteById(anyInt()))
                .thenReturn(Mono.just(getTestClient()));
    }

    @Test
    void getClientes(){
        Flux<Cliente> result = clienteController.getClientes();
        StepVerifier.create(result)
                .consumeNextWith(clienteResult -> assertEquals(getExpectedClient(),clienteResult))
                .verifyComplete();
    }

    @Test
    void getClienteById(){
        Cliente cliente = getTestClient();

        Mono<Cliente> result = clienteController.getClienteById(cliente.getId());
        StepVerifier.create(result)
                .consumeNextWith(clienteResult -> assertEquals(getExpectedClient(),clienteResult))
                .verifyComplete();
    }

    private static Cliente getExpectedClient(){
        return Cliente.builder()
                .id(1)
                .identificacion("TEL_3125486795")
                .nombre("Carlos Paredes")
                .direccion("cr 54 #25-30")
                .telefono("3125486795")
                .build();
    }

    private static Cliente getTestClient(){
        return Cliente.builder()
                .id(1)
                .identificacion("TEL_3125486795")
                .nombre("Carlos Paredes")
                .direccion("cr 54 #25-30")
                .telefono("3125486795")
                .build();
    }
}
