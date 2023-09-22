package com.sophossolutions.programacion.reactiva.seguimiento1.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserResponseDTO {
    private String username;
    private String token;
}
