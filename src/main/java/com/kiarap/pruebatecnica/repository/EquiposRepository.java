package com.kiarap.pruebatecnica.repository;

import com.kiarap.pruebatecnica.model.Equipos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquiposRepository extends JpaRepository<Equipos, Long> {

    @Query("SELECT e FROM Equipos e WHERE LOWER(e.nombre) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Equipos> findByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Equipos e WHERE LOWER(e.nombre) = LOWER(:nombre) AND (:id IS NULL OR e.id != :id)")
    boolean isNameDuplicated(@Param("nombre") String nombre, @Param("id") Long id);
}
