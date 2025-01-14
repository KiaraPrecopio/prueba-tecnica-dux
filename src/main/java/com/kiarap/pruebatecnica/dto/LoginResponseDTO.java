package com.kiarap.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la respuesta de la autenticación")
public class LoginResponseDTO {

    @Schema(description = "Token de autenticación")
    private String token;
}
