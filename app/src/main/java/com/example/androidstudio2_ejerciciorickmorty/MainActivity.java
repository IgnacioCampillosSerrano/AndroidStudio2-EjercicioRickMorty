package com.example.androidstudio2_ejerciciorickmorty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidstudio2_ejerciciorickmorty.adapters.CharacterAdapter;
import com.example.androidstudio2_ejerciciorickmorty.conexiones.ApiConexiones;
import com.example.androidstudio2_ejerciciorickmorty.conexiones.RetrofitObject;
import com.example.androidstudio2_ejerciciorickmorty.modelos.ApiRoot;
import com.example.androidstudio2_ejerciciorickmorty.modelos.Character;
import com.example.androidstudio2_ejerciciorickmorty.databinding.ActivityMainBinding;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //LISTA
    private ArrayList<Character> characters;

    //RECYCLER
    private CharacterAdapter adapter;
    private RecyclerView.LayoutManager lm;

    //API
    private Retrofit retrofit;
    private ApiConexiones api;

    //ApiRoot para almacenar la respuesta y poder acceder a la siguiente pagina

    private ApiRoot respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //INICIALIZAMOS LISTA
        characters = new ArrayList<>();

        //INICIALIZAMOS RECYCLER
        adapter = new CharacterAdapter(this, characters, R.layout.character_view_holder);
        lm = new GridLayoutManager(this, 2);

        binding.contenedorMain.setAdapter(adapter);
        binding.contenedorMain.setLayoutManager(lm);

        //INCIALIZAMOS CONEXION API
        retrofit = RetrofitObject.getConexion();
        api = retrofit.create(ApiConexiones.class);

        //CARGAR CONTENIDO INICIAL
        cargarDatos();

        //Al llegar al final del scroll, cojo la  url pagina next de la api y guardo el numero de pagina
        binding.contenedorMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(!recyclerView.canScrollVertically(1)){
                    if (respuesta.getInfo().getNext() != null){
                        String url = respuesta.getInfo().getNext();
                        String numPage = url.split("=")[1];
                        cargarSiguientePagina(numPage);
                    }
                }
            }
        });
    }

    private void cargarSiguientePagina(String numPage) {
        Call<ApiRoot> nuevaPagina = api.getPage(Integer.parseInt(numPage));
        nuevaPagina.enqueue(new Callback<ApiRoot>() {
            @Override
            public void onResponse(Call<ApiRoot> call, Response<ApiRoot> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK
                        && response.body() != null){
                    respuesta = response.body();
                    int tamanyo = characters.size();
                    characters.addAll(respuesta.getResults());
                    adapter.notifyItemRangeInserted(tamanyo, respuesta.getResults().size()); //Desde donde añado y cuantos
                }
            }

            @Override
            public void onFailure(Call<ApiRoot> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR AL CARGAR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarDatos() {

        Call<ApiRoot> doGetCarga = api.getCharacters();

        doGetCarga.enqueue(new Callback<ApiRoot>() {
            @Override
            public void onResponse(Call<ApiRoot> call, Response<ApiRoot> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK
                && response.body() != null){
                    respuesta = response.body();
                    characters.addAll(response.body().getResults());
                    adapter.notifyItemRangeInserted(0, characters.size());
                }
            }

            @Override
            public void onFailure(Call<ApiRoot> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR AL CARGAR ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}