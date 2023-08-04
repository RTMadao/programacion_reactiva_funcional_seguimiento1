package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Cliente;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    ClienteService clienteService;
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    public Flux<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Mono<Cliente> getClienteById(@PathVariable Integer id){
        return clienteService.getClienteById(id);
    }
}
