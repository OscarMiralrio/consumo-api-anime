package com.aluracursos.apianime.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAnimesPages(
        Pagination pagination
) {
}
