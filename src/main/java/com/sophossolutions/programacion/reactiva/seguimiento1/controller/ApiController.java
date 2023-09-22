package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/")
    public Mono<String> getClientes(){
        return Mono.just("Puedes acceder al API mediante token");
    }
}
