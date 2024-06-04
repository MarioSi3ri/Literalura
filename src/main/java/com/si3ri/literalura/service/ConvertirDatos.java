package com.si3ri.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ConvertirDatos implements IConvertirDatos { // Clase que implementa la interfaz 'IConvertirDatos'.
    private final ObjectMapper objectMapper = new ObjectMapper(); // Crea un nuevo objeto 'ObjectMapper', para convertir datos entre diferentes formatos, en este caso, se utiliza para convertir entre objetos Java y JSON.
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) { // El método toma una cadena JSON y una clase como entrada, y devuelve un objeto de esa clase.
        try {
            JsonNode rootNode = objectMapper.readTree(json); // El método 'readTree()' del 'ObjectMapper' convierte la cadena JSON en un 'JsonNode'.

            JsonNode resultsArray = rootNode.get("results");

            if (resultsArray != null && resultsArray.size() > 0) {
                JsonNode firstResult = resultsArray.get(0); // Si el array “results” tiene al menos un elemento, esta línea obtiene el primer objeto del array.
                return objectMapper.treeToValue(firstResult, clase); // Convierte el primer objeto del array “results” en un objeto de la clase especificada.
            } else {
                throw new RuntimeException("No se encontraron resultados en el JSON.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obtenerDatosArray(String json, Class<T> clase) { // Devuelve una lista de objetos de la clase.
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode resultsArray = rootNode.get("results");

            if (resultsArray != null && resultsArray.size() > 0) {
                List<T> resultList = new ArrayList<>();
                for (JsonNode node : resultsArray) {
                    T result = objectMapper.treeToValue(node, clase);
                    resultList.add(result);
                }
                return resultList;
            } else {
                throw new RuntimeException("No se encontraron resultados en el JSON.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}