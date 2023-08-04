package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.OrdenLocal;
import com.sophossolutions.programacion.reactiva.seguimiento1.repository.OrdenLocalRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrdenLocalService {

    OrdenLocalRepository ordenLocalRepository;
    public OrdenLocalService (OrdenLocalRepository ordenLocalRepository){
        this.ordenLocalRepository = ordenLocalRepository;
    }

    public Flux<OrdenLocal> getAllOrdenLocal(){
        return ordenLocalRepository.findAll();
    }

    public Mono<OrdenLocal> getOrdenLocalById(Integer id){
        return ordenLocalRepository.findById(id);
    }

    public Mono<OrdenLocal> createOrdenLocal(OrdenLocal ordenLocal){
        return UtilService.calcularTotalPedido(ordenLocal, ordenLocal.getPlatos().stream())
                .map(OrdenLocal.class::cast)
                .flatMap(ordenLocalRepository::save);
    }

    public Mono<OrdenLocal> modificarOrdenLocal(OrdenLocal ordenLocal){
        return ordenLocalRepository.save(ordenLocal);
    }

    public Mono<Boolean> deleteOrdenLocal(Integer id){
        return ordenLocalRepository.deleteById(id)
                .thenReturn(Boolean.TRUE)
                .onErrorReturn(Boolean.FALSE);
    }
}
