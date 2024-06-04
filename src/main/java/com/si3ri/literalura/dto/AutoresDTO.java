package com.si3ri.literalura.dto;

public record AutoresDTO (Long Id, String nombre, int nacimiento, int deceso) {
    // Se accede a los campos de 'AutoresDTO' utilizando los métodos getter generados (id(), nombre(), nacimiento(), deceso()) reduciendo el código repetitivo.
}