package com.aluracursos.Literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String obtenerDatos(String url) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return "";
        } catch (InterruptedException e) {
            System.out.println("La petición fue interrumpida: " + e.getMessage());
            return "";
        }

        String json = response.body();
        return json;
    }
}
