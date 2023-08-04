package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Plato;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/menu")
public class MenuController {

    MenuService menuService;
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/")
    public Flux<Plato> getMenu(){
        return menuService.getMenu();
    }

    @GetMapping("/{id}")
    public Mono<Plato> getPlatoById(@PathVariable Integer id){
        return menuService.getPlatoById(id);
    }
}
