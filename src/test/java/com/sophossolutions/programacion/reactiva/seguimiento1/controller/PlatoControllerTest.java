package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Cliente;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Plato;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.MenuService;
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
class PlatoControllerTest {

    @InjectMocks
    MenuController menuController;

    @Mock
    MenuService menuService;

    @BeforeEach
    public void init() {
        when(menuService.getMenu())
                .thenReturn(Flux.just(getTestPlato()));
        when(menuService.getPlatoById(anyInt()))
                .thenReturn(Mono.just(getTestPlato()));
    }

    @Test
    void getMenu(){
        Flux<Plato> result = menuController.getMenu();
        StepVerifier.create(result)
                .consumeNextWith(menuResult -> assertEquals(getExpectedPlato(),menuResult))
                .verifyComplete();
    }

    @Test
    void getPlatoById(){
        Plato plato = getTestPlato();

        Mono<Plato> result = menuController.getPlatoById(plato.getId());
        StepVerifier.create(result)
                .consumeNextWith(platoResult -> assertEquals(getExpectedPlato(),platoResult))
                .verifyComplete();
    }

    private static Plato getExpectedPlato(){
        return Plato.builder()
                .id(1)
                .nombre("Sopa")
                .descripcion("Deliciosa sopa de hueso")
                .precio(7000.00)
                .build();
    }

    private static Plato getTestPlato(){
        return Plato.builder()
                .id(1)
                .nombre("Sopa")
                .descripcion("Deliciosa sopa de hueso")
                .precio(7000.00)
                .build();
    }
}
