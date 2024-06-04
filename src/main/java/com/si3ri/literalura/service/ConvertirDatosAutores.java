package com.si3ri.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ConvertirDatosAutores implements IConvertirDatos { // Clase que implementa la interfaz 'IConvertirDatos'.
    private final ObjectMapper objectMapper = new ObjectMapper(); // 'ObjectMapper' es una herramienta de la biblioteca 'Jackson' para convertir datos entre diferentes formatos.

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) { // Toma una cadena JSON y una clase como entrada, y devuelve un objeto de esa clase.
        try {
            JsonNode rootNode = objectMapper.readTree(json); // Utiliza el método 'readTree' del 'ObjectMapper' para convertir la cadena JSON en un 'JsonNode'.

            JsonNode resultsArray = rootNode.get("results");

            if (resultsArray != null && resultsArray.size() > 0) {
                JsonNode firstResult = resultsArray.get(0).get("authors").get(0);
                return objectMapper.treeToValue(firstResult, clase);
            } else {
                throw new RuntimeException("No se encontraron resultados en el JSON.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obtenerDatosArray(String json, Class<T> clase) { // Este método en lugar de devolver un solo objeto, devuelve una lista de objetos.
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode resultsArray = rootNode.get("results");
            if (resultsArray != null && resultsArray.size() > 0) {
                List<T> resultList = new ArrayList<>();
                for (int i = 0; i < resultsArray.size(); i++) { // Itera sobre cada objeto en el array “results” en el JSON, obteniendo el primer objeto del array “authors” en cada objeto.
                    JsonNode firstResult = resultsArray.get(i).get("authors").get(0);
                    T result = objectMapper.treeToValue(firstResult, clase);
                    resultList.add(result);
                }
                return resultList;
            } else {
                throw new RuntimeException("No se encontraron resultados en el JSON.");
            }
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}