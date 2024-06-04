package com.si3ri.literalura.service;

import com.si3ri.literalura.dto.AutoresDTO;
import com.si3ri.literalura.model.Autores;
import com.si3ri.literalura.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Especialización de '@Component' y se usa para indicar que la clase es un servicio.
public class AutoresService {
    @Autowired
    private AutoresRepository repositorio;

    public List<AutoresDTO> obtenerLosAutores() {
        return convertirDatos(repositorio.findAll()); // Utiliza el método 'findAll()' del repositorio para obtener todos los autores y luego convierte cada autor a 'AutoresDTO' usando el método 'convertirDatos'.
    }

    public List<AutoresDTO> convertirDatos(List<Autores> autor) { // El método toma una lista de autores y la convierte en una lista de 'AutoresDTO'.
        return autor.stream() // Convierte la lista en un stream.
                .map(a -> new AutoresDTO(
                        a.getId(),
                        a.getNombre(),
                        a.getNacimiento(),
                        a.getDeceso()
                ))
                .collect(Collectors.toList()); // Convierte el stream de nuevo en una lista.
    }
}