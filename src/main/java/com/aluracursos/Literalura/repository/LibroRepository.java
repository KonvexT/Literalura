package com.aluracursos.Literalura.repository;

import com.aluracursos.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdioma(String idioma);

    @Query("SELECT l FROM Libro l ORDER BY l.descargas DESC")
    List<Libro> librosMasDescargados();
}