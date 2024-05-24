package com.aluracursos.apianime.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Anime(
        String title,
        String type,
        Integer episodes,
        String status,
        String duration,
        Double score,
        Integer rank,
        String season,
        Integer year
) {
}
