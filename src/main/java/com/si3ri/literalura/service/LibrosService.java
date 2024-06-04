package com.si3ri.literalura.service;

import com.si3ri.literalura.dto.LibrosDTO;
import com.si3ri.literalura.model.Libros;
import com.si3ri.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Especialización de '@Component' y se usa para indicar que la clase es un servicio.
public class LibrosService {

    @Autowired // Anotación que se utiliza para inyectar automáticamente el bean 'LibrosRepository' en esta clase de servicio.
    private LibrosRepository repositorio;

    public List<LibrosDTO> obtenerLibros() {
        return convertirDatos(repositorio.findAll()); // Método que devuelve una lista de todos los libros. Utiliza el método 'findAll()' del repositorio para obtener todos los libros y luego convierte cada libro a 'LibrosDTO' usando el método 'convertirDatos()'.
    }

    public List<LibrosDTO> convertirDatos(List<Libros> libro) { // Este método toma una lista de libros y la convierte en una lista de 'LibrosDTO'.
        return libro.stream() // Operación 'stream()' para convertir la lista en un stream.
                .map(l -> new LibrosDTO( // 'map()' para aplicar una función a cada elemento del stream.
                        l.getId(),
                        l.getTitulo(),
                        l.getAutor(),
                        l.getIdioma(),
                        l.getDescargas()
                ))
                .collect(Collectors.toList()); // 'collect()' para convertir el stream de nuevo en una lista.
    }
}