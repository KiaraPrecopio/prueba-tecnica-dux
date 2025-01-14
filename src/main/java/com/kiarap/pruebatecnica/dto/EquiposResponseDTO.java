package com.kiarap.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la respuesta de los equipos")
public class EquiposResponseDTO {

    @Schema(description = "Id del equipo")
    private Long id;

    @Schema(description = "Nombre del equipo")
    private String nombre;

    @Schema(description = "Liga del equipo")
    private String liga;

    @Schema(description = "País del equipo")
    private String pais;
}
