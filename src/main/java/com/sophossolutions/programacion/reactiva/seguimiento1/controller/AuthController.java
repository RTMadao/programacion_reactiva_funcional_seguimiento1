package com.sophossolutions.programacion.reactiva.seguimiento1.controller;

import com.sophossolutions.programacion.reactiva.seguimiento1.controller.dto.UserResponseDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.User;
import com.sophossolutions.programacion.reactiva.seguimiento1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public Mono<ResponseEntity<UserResponseDTO>> createOrdenLocal(@RequestBody User user){
        return userService.generateToken(user)
                .map(userResponseDTO -> new ResponseEntity<>(userResponseDTO, HttpStatus.OK))
                .switchIfEmpty( Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
