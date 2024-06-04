package com.si3ri.literalura.service;

import java.util.List;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase); // Es un método de la interfaz que toma una cadena JSON y una clase como entrada, y devuelve un objeto de esa clase.
    <T> List<T> obtenerDatosArray(String json, Class<T> clase); // En lugar de devolver un solo objeto, devuelve una lista de objetos: <T> es un método genérico, lo que significa que puede trabajar con cualquier tipo de objeto.
}