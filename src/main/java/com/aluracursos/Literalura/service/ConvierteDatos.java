package com.aluracursos.Literalura.service;

import com.aluracursos.Literalura.model.DatosRespuesta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public DatosRespuesta obtenerDatosRespuesta(String json) {
        try {
            return objectMapper.readValue(json, DatosRespuesta.class);
        } catch (JsonProcessingException e) {
            System.out.println("Error al convertir JSON: " + e.getMessage());
            return null;
        }
    }
}
