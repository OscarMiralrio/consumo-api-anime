package com.aluracursos.apianime.principal;

import com.aluracursos.apianime.modelos.Anime;
import com.aluracursos.apianime.modelos.DatosAnimes;
import com.aluracursos.apianime.service.ConsumoAPI;
import com.aluracursos.apianime.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();

    private final String URL_BASE = "https://api.jikan.moe/v4/anime";
    private final String URL_PAGINATION = "?page=";

    public void muestraDatos(){

        List<Anime> animeList = obtieneAnimesList(obtieneDatosAnimes(URL_BASE+URL_PAGINATION));

        System.out.println("====================================================");
        //TOP 10 ANIMER MEJOR RANKEADOS DE LA LISTA OBTENIDA
        System.out.println("TOP 10 ANIMES MEJOR RANKEADOS");
        animesMejorRankeados(animeList);

        System.out.println("====================================================");
        busquedaDeAnimePorNombre(animeList);

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

    private void animesMejorRankeados(List<Anime> animeList){
        animeList.stream()
                .filter( a -> a.rank() != null)
                .sorted(Comparator.comparing(Anime::rank).reversed())
                .limit(10)
                .forEach( a -> System.out.println(" Anime :: " + a.title() + " Rank :: " + a.rank()));
    }

    private void busquedaDeAnimePorNombre(List<Anime> animeList){
         //Busca episodio por pedazo de titulo
        System.out.println("Por favor escriba el titulo del Anime que desea ver");
        var pedazoTitulo = teclado.nextLine();

        List<Anime> animeBuscado = animeList.stream()
                .filter(e -> e.title().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                .collect(Collectors.toList());

        System.out.println("====================================================");
        System.out.println("Animes encontrados que conicidesn con :: " +pedazoTitulo);
        System.out.println("====================================================");
        animeBuscado.stream().forEach( a -> System.out.println("Anime :: " +a.title()));

    }


}
