package com.aluracursos.apianime.service;

public interface IConvierteDatos {

    <T> T convierteDatos(String json, Class<T> clase);

}
