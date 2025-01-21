package com.kiarap.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO para la petición de login")
public class UserRequestDTO {

    @Schema(description = "Nombre de usuario")
    private String username;

    @Schema(description = "Contraseña")
    private String password;
}
