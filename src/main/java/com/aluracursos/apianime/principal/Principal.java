package com.aluracursos.apianime.principal;

import com.aluracursos.apianime.modelos.Anime;
import com.aluracursos.apianime.modelos.DatosAnimes;
import com.aluracursos.apianime.service.ConsumoAPI;
import com.aluracursos.apianime.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {


    private final ConsumoAPI consumoAPI = new ConsumoAPI();

    private final ConvierteDatos convierteDatos = new ConvierteDatos();

    private final String URL_BASE = "https://api.jikan.moe/v4/anime";
    private final String URL_PAGINATION = "?page=";

    public void muestraDatos(){

        obtieneAnimesList(obtieneDatosAnimes(URL_BASE+URL_PAGINATION))
                .stream()
                .forEach(System.out::println);

    }

    private List<DatosAnimes> obtieneDatosAnimes(String url){
        // Obtiene la data de los animes y los agrega a una lista del tipo ANIME
        List<DatosAnimes> datosAnimes = new ArrayList<>();
        for (int i = 1; i <=10; i++) {
            var json = consumoAPI.obtenerDatos(url+i);
            var dataAnime = convierteDatos.convierteDatos(json, DatosAnimes.class);
            datosAnimes.add(dataAnime);
        }
        return datosAnimes;
    }

    private List<Anime> obtieneAnimesList(List<DatosAnimes> datosAnimes){
        return datosAnimes.stream()
                .flatMap( a -> a.data().stream())
                .collect(Collectors.toList());
    }


}
