package com.sophossolutions.programacion.reactiva.seguimiento1.service;

import com.sophossolutions.programacion.reactiva.seguimiento1.model.Cliente;
import com.sophossolutions.programacion.reactiva.seguimiento1.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {

    private static final String  CLIENT_IDENTIFICATION_TYPE = "TEL";
    ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Flux<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }
    public Mono<Cliente> getClienteById(Integer id){
        return clienteRepository.findById(id);
    }

    public Mono<Cliente> createClient(Cliente cliente){
        return setClientIdentification(cliente)
                .flatMap(clienteRepository::save);
    }

    public Mono<Cliente> setClientIdentification(Cliente cliente){
        return Mono.just(cliente.toBuilder()
                        .identificacion(mapClientIdentification(cliente))
                .build());
    }

    public String mapClientIdentification(Cliente cliente){
        return cliente.mapIdentificacion(CLIENT_IDENTIFICATION_TYPE, cliente.getTelefono());
    }
}
