package com.aluracursos.Literalura.repository;

import com.aluracursos.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anoNacimiento <= :ano AND (a.anoMuerte IS NULL OR a.anoMuerte >= :ano)")
    List<Autor> autoresVivosEnElAno(@Param("ano") Integer ano);
}
