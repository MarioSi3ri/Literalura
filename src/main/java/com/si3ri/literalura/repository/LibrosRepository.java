package com.si3ri.literalura.repository;

import com.si3ri.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros,Long> {
    Optional<Libros> findByTituloContainingIgnoreCase(String nombreLibro); // El resultado se envuelve en un 'Optional' para indicar que la consulta puede no devolver ningún resultado si no se encuentra ningún libro que coincida.
}