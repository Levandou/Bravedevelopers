package com.example.bravedevelopers.presentation.firstScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.DeleteFromFavoritesUseCase
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PokemonsListAdapter(private val addToFavorites: (InformationAboutPokemon) ->Unit,
                          private val deleteFromFavorites: (InformationAboutPokemon) -> Unit
):RecyclerView.Adapter<PokemonsListAdapter.PokemonsViewHolder>() {


    var pokemonsListFromDb = listOf<InformationAboutPokemon>()
    var pokemonsList = listOf<InformationAboutPokemon>()
   // var pokemonsListFromDb2 =pokemonsListFromDb.toMutableList()
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

           for (i in pokemonsListFromDb) {
                if (pokemonsList[position].id == i.id || pokemonsList[position].isFavorite) {
                    pokemonsList[position].isFavorite = true
                    holder.ivAddFavorites.setImageResource(R.drawable.star1)
                }

               else{
                    pokemonsList[position].isFavorite = false
                    holder.ivAddFavorites.setImageResource(R.drawable.star2)
                }
            }

        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$position2.png")
            .into(holder.ivLogoPokemon)

        holder.ivAddFavorites.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if(pokemonsList[position].isFavorite){
                    /*for (i in pokemonsListFromDb2) {
                        if (pokemonsList[position].id == i.id) {
                            pokemonsListFromDb2.removeAt(position)
                        }
                    }*/
                    holder.ivAddFavorites.setImageResource(R.drawable.star2)
                        pokemonsList[position].isFavorite = false
                        deleteFromFavorites(pokemonsList[position])

                }
                else{
                  /*  for (i in pokemonsListFromDb2) {
                        if (pokemonsList[position].id == i.id) {
                            pokemonsListFromDb2.removeAt(position)
                        }
                    }*/

                        holder.ivAddFavorites.setImageResource(R.drawable.star1)
                        pokemonsList[position].isFavorite = true
                        addToFavorites(pokemonsList[position])
                }
               Log.d("click", holder.ivAddFavorites.drawable.constantState.toString())
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