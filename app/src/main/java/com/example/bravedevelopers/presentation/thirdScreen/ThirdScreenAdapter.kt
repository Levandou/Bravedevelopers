package com.example.bravedevelopers.presentation.thirdScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.squareup.picasso.Picasso

class ThirdScreenAdapter(private val deleteFromFavorites: (InformationAboutPokemon) -> Unit)
:RecyclerView.Adapter<ThirdScreenAdapter.ThirdViewHolder>()

{
 //  var pokemonsListFromDb =listOf<InformationAboutPokemon>()
    var pokemonsListFromDb= mutableListOf<InformationAboutPokemon>()

        set(value) { field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pokemon,
            parent,
            false
        )
        return ThirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        val pokemonItem = pokemonsListFromDb[position]
        val itemId=pokemonsListFromDb[position].id
        holder.tvName.text = pokemonItem.name

        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$itemId.png")
            .into(holder.ivLogoPokemon)

        holder.ivAddFavorites.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                deleteFromFavorites(pokemonsListFromDb[position])
               pokemonsListFromDb.remove(pokemonsListFromDb[position])
                notifyItemRemoved(position)
                notifyItemRangeRemoved(position,pokemonsListFromDb.size)
                Log.d("click", holder.ivAddFavorites.drawable.constantState.toString())
            }
        })

    }

    override fun getItemCount(): Int {
        return pokemonsListFromDb.size
    }

    class ThirdViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivAddFavorites=view.findViewById<ImageView>(R.id.ivAddFavorites)
        val ivLogoPokemon = view.findViewById<ImageView>(R.id.ivLogoPokemon)
        val tvName = view.findViewById<TextView>(R.id.tvName)
    }
}


