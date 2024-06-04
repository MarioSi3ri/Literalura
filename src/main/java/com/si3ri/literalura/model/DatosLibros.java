package com.si3ri.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Indica que cualquier propiedad desconocida en el JSON que no se corresponda con los campos en esta clase se ignorará durante la deserialización.
public record DatosLibros(
        @JsonAlias("title") String titulo, // Especifica que el campo 'titulo' en la clase 'DatosLibros' corresponde a la propiedad 'title' en el JSON.
        @JsonAlias("name") Autores autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double descargas){
}