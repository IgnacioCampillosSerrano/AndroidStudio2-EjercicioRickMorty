package com.example.androidstudio2_ejerciciorickmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidstudio2_ejerciciorickmorty.conexiones.ApiConexiones;
import com.example.androidstudio2_ejerciciorickmorty.conexiones.RetrofitObject;
import com.example.androidstudio2_ejerciciorickmorty.databinding.ActivityVerCharacterBinding;
import com.example.androidstudio2_ejerciciorickmorty.modelos.Character;
import com.squareup.picasso.Picasso;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerCharacterActivity extends AppCompatActivity {

    private ApiConexiones api;

    private ActivityVerCharacterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerCharacterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Coger el ID que pasamos en el adapter

        if(getIntent().getExtras() != null){
            int id = getIntent().getExtras().getInt("ID");

            api = RetrofitObject.getConexion().create(ApiConexiones.class);
            Call<Character> doGetCharacter = api.getCharacter(id);

            doGetCharacter.enqueue(new Callback<Character>() {
                @Override
                public void onResponse(Call<Character> call, Response<Character> response) {
                    if (response.code() == HttpsURLConnection.HTTP_OK
                    && response.body() != null){
                        Character c = response.body();

                        binding.lbNombreVerCharacter.setText(c.getName());
                        binding.lbEspecieVerCharacter.setText(c.getSpecies());
                        binding.lbGeneroVerCharacter.setText(c.getGender());

                        Picasso.get()
                                .load(c.getImage())
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_foreground)
                                .into(binding.imgFotoVerCharacter);
                    }
                }

                @Override
                public void onFailure(Call<Character> call, Throwable t) {
                    Toast.makeText(VerCharacterActivity.this, "ERROR AL CARGAR", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}