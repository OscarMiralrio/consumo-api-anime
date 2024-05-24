package com.aluracursos.apianime.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAnimes(
        List<Anime> data
) {
}
