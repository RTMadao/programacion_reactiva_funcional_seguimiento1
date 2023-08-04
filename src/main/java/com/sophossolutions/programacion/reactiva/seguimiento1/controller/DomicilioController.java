package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.dto.DeleteConfirmationDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.Domicilio;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.DomicilioService;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.UtilService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedido/domicilio")
public class DomicilioController {

    DomicilioService domicilioService;
    public DomicilioController(DomicilioService domicilioService){
        this.domicilioService = domicilioService;
    }

    @GetMapping("/")
    public Flux<Domicilio> getDomicilio(){
        return domicilioService.getAllDomicilio();
    }

    @GetMapping("/{id}")
    public Mono<Domicilio> getDomicilioById(@PathVariable Integer id){
        return domicilioService.getDomicilioById(id);
    }

    @PostMapping("/")
    public Mono<Domicilio> createDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.createDomicilio(domicilio);
    }

    @PutMapping("/")
    public Mono<Domicilio> updateDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.modificarDomicilio(domicilio);
    }

    @DeleteMapping("/{id}")
    public Mono<DeleteConfirmationDTO> deleteDomicilio(@PathVariable Integer id){
        return domicilioService.deleteDomicilio(id)
                .map(isSuccessfullyDelete -> DeleteConfirmationDTO.builder()
                        .isSuccessfullyDelete(isSuccessfullyDelete)
                        .message(UtilService.setDeleteMessage(isSuccessfullyDelete))
                        .build());
    }
}
