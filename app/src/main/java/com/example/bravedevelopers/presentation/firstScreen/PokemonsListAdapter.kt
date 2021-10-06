package com.example.bravedevelopers.presentation.firstScreen

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.AddToFavoritesUseCase
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PokemonsListAdapter  : RecyclerView.Adapter<PokemonsListAdapter.PokemonsViewHolder>() {

    @Inject
lateinit var addToFavoritesUseCase:AddToFavoritesUseCase

    var pokemonsList = listOf<InformationAboutPokemon>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pokemon,
            parent,
            false
        )
        return PokemonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        val pokemonItem = pokemonsList[position]
        val position2=pokemonsList[position].id
        holder.tvName.text = pokemonItem.name
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$position2.png")
            .into(holder.ivLogoPokemon)

 /*   holder.ivAddFavorites.setOnClickListener(){

    }*/

        holder.ivAddFavorites.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                if(pokemonsList[position].isFavorite){
                    holder.ivAddFavorites.setImageResource(R.drawable.star2)
                    addToFavoritesUseCase.addToFavorites(pokemonsList[position])
                pokemonsList[position].isFavorite=false
                }
                else{
                    holder.ivAddFavorites.setImageResource(R.drawable.star1)
                    pokemonsList[position].isFavorite=true
                }
               Log.d("click", holder.ivAddFavorites.drawable.constantState.toString())


//                if(holder.ivAddFavorites.drawable.constantState==ContextCompat.getDrawable(,R.drawable.star))
            }

        })

    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

    class PokemonsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivAddFavorites=view.findViewById<ImageView>(R.id.ivAddFavorites)
        val ivLogoPokemon = view.findViewById<ImageView>(R.id.ivLogoPokemon)
        val tvName = view.findViewById<TextView>(R.id.tvName)


    }


}