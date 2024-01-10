package com.example.androidstudio2_ejerciciorickmorty.conexiones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {
    public static final String BASE_URL="https://rickandmortyapi.com";

    public static Retrofit getConexion(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
