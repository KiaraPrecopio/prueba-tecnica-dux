package com.kiarap.pruebatecnica.repository;

import com.kiarap.pruebatecnica.model.Equipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquiposRepository extends JpaRepository<Equipos, Long> {

    @Query("SELECT e FROM Equipos e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%',:name,'%'))")
    Equipos findByName(@Param("name") String name);
}
