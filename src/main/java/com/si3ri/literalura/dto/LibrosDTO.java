package com.si3ri.literalura.dto;

import com.si3ri.literalura.model.Autores;

public record LibrosDTO (Long Id, String titulo, Autores autor, String idioma, Double descargas) {
    // Se accede a los campos de 'LibrosDTO' utilizando los métodos getter generados (id(), titulo(), autor(), idioma(), descargas()), evitando el código repetitivo.
}