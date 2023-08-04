package com.sophossolutions.programacion.reactiva.seguimiento1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteConfirmationDTO {
    private Boolean isSuccessfullyDelete;
    private String message;
}
