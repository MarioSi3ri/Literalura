package com.si3ri.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url){ // Toma una URL como entrada y devuelve una cadena que representa los datos obtenidos de la API.
        HttpClient client = HttpClient.newHttpClient(); // Se crea un nuevo objeto 'HttpClient', que se utiliza para enviar solicitudes HTTP y recibir respuestas HTTP.
        HttpRequest request = HttpRequest.newBuilder() // Crea un nuevo objeto 'HttpRequest', que representa una solicitud HTTP para enviar al servidor.
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null; // Declara un objeto 'HttpResponse' que se utilizará para almacenar la respuesta del servidor o API.
        try { // Bloque que intenta enviar la solicitud HTTP y recibir la respuesta.
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body(); // Extrae el cuerpo de la respuesta HTTP, que se supone que es una cadena JSON, y la almacena en la variable json.
        return json;
    }
}