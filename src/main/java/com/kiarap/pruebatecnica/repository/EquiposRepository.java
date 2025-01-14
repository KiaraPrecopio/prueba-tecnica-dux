package com.kiarap.pruebatecnica.repository;

import com.kiarap.pruebatecnica.model.Equipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquiposRepository extends JpaRepository<Equipos, Long> {

}
