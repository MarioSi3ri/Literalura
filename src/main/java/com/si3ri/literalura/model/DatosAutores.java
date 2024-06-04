package com.si3ri.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Maneja cambios en la estructura del JSON sin causar errores.
public record DatosAutores(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int nacimiento,
        @JsonAlias("death_year") int deceso) { //  Especifica que el campo 'deceso' en la clase 'DatosAutores' corresponde a la propiedad 'death_year' en el JSON.
}