package com.kiarap.pruebatecnica.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Estructura estándar para errores")
public class ErrorResponse {

    @Schema(description = "Código de estado HTTP", example = "404")
    private int status;

    @Schema(description = "Descripción del error", example = "Not Found")
    private String error;

    @Schema(description = "Mensaje detallado del error", example = "Elemento no encontrado")
    private String message;
}