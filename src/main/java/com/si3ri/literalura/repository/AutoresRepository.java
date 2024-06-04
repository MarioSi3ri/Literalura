package com.si3ri.literalura.repository;

import com.si3ri.literalura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> { // Interfaz que extiende a 'JpaRepository' aplicado en entidad de tipo Autores.
    Optional<Autores> findByNombreContainingIgnoreCase(String nombreAutor); // El método buscará Autores cuyo nombre contenga la cadena 'nombreAutor' proporcionada, sin tener en cuenta las mayúsculas y minúsculas.
}