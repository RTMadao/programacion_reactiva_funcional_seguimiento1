package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Plato;
import com.sophossolutions.programacion.reactiva.seguimiento1.repository.PlatoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MenuService {

    PlatoRepository platoRepository;
    public MenuService(PlatoRepository platoRepository){
        this.platoRepository = platoRepository;
    }

    public Flux<Plato> getMenu(){
        return platoRepository.findAll();
    }
    public Mono<Plato> getPlatoById(Integer id){
        return platoRepository.findById(id);
    }
}
