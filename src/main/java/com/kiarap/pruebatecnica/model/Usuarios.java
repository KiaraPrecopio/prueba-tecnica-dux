package com.kiarap.pruebatecnica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuarios {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;
}
