package com.kiarap.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la petición de login")
public class UserRequestDTO {

    @Schema(description = "Nombre de usuario")
    private String username;

    @Schema(description = "Contraseña")
    private String password;

}
