package com.example.androidstudio2_ejerciciorickmorty.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstudio2_ejerciciorickmorty.R;
import com.example.androidstudio2_ejerciciorickmorty.VerCharacterActivity;
import com.example.androidstudio2_ejerciciorickmorty.modelos.Character;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterVH> {
    private Context context;
    private List<Character> objects;
    private int resource;

    public CharacterAdapter(Context context, List<Character> objects, int resource) {
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public CharacterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View characterView = LayoutInflater.from(context).inflate(resource, null);
        characterView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new CharacterVH(characterView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterVH holder, int position) {
        Character character = objects.get(position);

        holder.lbName.setText(character.getName());
        Picasso.get()
                .load(character.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgCharacter);

        //Al hacer click en un item,abrir y pasar el ID de ese item a una nueva actividad
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VerCharacterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", character.getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class CharacterVH extends RecyclerView.ViewHolder{

            ImageView imgCharacter;
            TextView lbName;
            public CharacterVH(@NonNull View itemView) {
                super(itemView);

                imgCharacter = itemView.findViewById(R.id.imgCharacterCard);
                lbName = itemView.findViewById(R.id.lbNameCharacterCard);
            }
        }
}
