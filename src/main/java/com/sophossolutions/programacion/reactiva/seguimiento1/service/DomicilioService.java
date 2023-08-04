package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Domicilio;
import com.sophossolutions.programacion.reactiva.seguimiento1.repository.DomicilioRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DomicilioService {

    DomicilioRepository domicilioRepository;
    public DomicilioService (DomicilioRepository domicilioRepository){
        this.domicilioRepository = domicilioRepository;
    }

    public Flux<Domicilio> getAllDomicilio(){
        return domicilioRepository.findAll();
    }

    public Mono<Domicilio> getDomicilioById(Integer id){
        return domicilioRepository.findById(id);
    }

    public Mono<Domicilio> createDomicilio(Domicilio domicilio){
        return UtilService.calcularTotalPedido(domicilio, domicilio.getPlatos().stream())
                .map(Domicilio.class::cast)
                .flatMap(domicilioRepository::save);
    }

    public Mono<Domicilio> modificarDomicilio(Domicilio domicilio){
        return domicilioRepository.save(domicilio);
    }

    public Mono<Boolean> deleteDomicilio(Integer id){
        return domicilioRepository.deleteById(id)
                .thenReturn(Boolean.TRUE)
                .onErrorReturn(Boolean.FALSE);
    }
}
