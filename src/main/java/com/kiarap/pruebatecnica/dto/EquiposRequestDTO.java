package com.kiarap.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO para la creación de equipos")
public class EquiposRequestDTO {

    @Schema(description = "Nombre del equipo", example = "Equipo 1")
    private String nombre;

    @Schema(description = "Liga del equipo", example = "Liga 1")
    private String liga;

    @Schema(description = "País del equipo", example = "Pais 1")
    private String pais;
}
