package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.dto.DeleteConfirmationDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.OrdenLocalService;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.UtilService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedido/local")
public class OrdenLocalController {

    OrdenLocalService ordenLocalService;
    public OrdenLocalController(OrdenLocalService ordenLocalService){
        this.ordenLocalService = ordenLocalService;
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
}
