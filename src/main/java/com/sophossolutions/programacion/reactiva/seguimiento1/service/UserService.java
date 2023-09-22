package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.sophossolutions.programacion.reactiva.seguimiento1.controller.dto.UserResponseDTO;
import com.sophossolutions.programacion.reactiva.seguimiento1.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UtilJWT utilJWT;

    public UserService(UtilJWT utilJWT) {
        this.utilJWT = utilJWT;
    }

    public Flux<User> userList(){
        List<User> list = new ArrayList<>();
        list.add(User.builder()
                .id(1)
                .username("carlos")
                .password("1234")
                .role("ADMIN")
                .build());
        list.add(User.builder()
                .id(2)
                .username("Daniel")
                .password("1234")
                .role("USER")
                .build());
        list.add(User.builder()
                .id(3)
                .username("Maria")
                .password("1234")
                .role("USER")
                .build());
        return Flux.fromIterable(list);
    }

    public Mono<UserDetails> findUserByUsername(String username){
        return this.userList()
                .filter(user -> user.getUsername().equals(username))
                .next()
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build());
    }

    public Mono<UserResponseDTO> generateToken(User user){
        return this.findUserByUsername(user.getUsername())
                .filter(userDetails -> userDetails.getPassword().equals(user.getPassword()))
                .switchIfEmpty(Mono.empty())
                .map(userDetails -> UserResponseDTO.builder()
                        .username(userDetails.getUsername())
                        .token(utilJWT.generateToken(userDetails))
                        .build());
    }
}
