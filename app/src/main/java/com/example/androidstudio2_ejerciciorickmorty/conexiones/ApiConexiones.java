package com.example.androidstudio2_ejerciciorickmorty.conexiones;

import com.example.androidstudio2_ejerciciorickmorty.modelos.ApiRoot;
import com.example.androidstudio2_ejerciciorickmorty.modelos.Character;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {
    //Descargar la informacion de la primera pagina de characters

    @GET("/api/character")
    Call<ApiRoot> getCharacters();

    //Descargar la informacion de un personaje en concreto segun su ID

    @GET("/api/character/{idCharacter}")
    Call<Character> getCharacter(@Path("idCharacter") int id);

    //Descargar la informacion de otra pagina que no sea la primera

    @GET("/api/character?")
    Call<ApiRoot> getPage(@Query("page") int pagina);
}
