package com.si3ri.literalura.controller;

import com.si3ri.literalura.dto.LibrosDTO;
import com.si3ri.literalura.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/libros") // Establece la ruta base para todas las solicitudes manejadas por este controlador: todas las rutas comenzarán con '/libros'.
public class LibrosController {
    @Autowired
    private LibrosService servicioLibros; // Inyección que permite al controlador usar los métodos definidos en 'LibrosService'.

    @GetMapping()
    public List<LibrosDTO> obtenerLibros(){ // El servicio 'LibroService' devuelve una lista de 'LibrosDTO' que se envía de vuelta en formato JSON.
        return servicioLibros.obtenerLibros();
    }
}