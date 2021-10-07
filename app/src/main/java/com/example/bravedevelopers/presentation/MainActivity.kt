package com.example.bravedevelopers.presentation


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.presentation.firstScreen.MainViewModel
import com.example.bravedevelopers.presentation.firstScreen.PokemonsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: PokemonsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /* setupRecyclerView()
         var list:MutableList<InformationAboutPokemon> = mutableListOf()
        mainViewModel.pokemonsList.observe(this ,{
            list.add(it[0])
            adapter.pokemonsList=list
            Log.d("zxcvb", it.toString())
        })
*/
    }
/*
    private fun setupRecyclerView(){
        val rvPokemonsList=findViewById<RecyclerView>(R.id.rv_pokemon_list)
        adapter= PokemonsListAdapter{mainViewModel.addToFavorites(it)}
        rvPokemonsList.adapter=adapter
    }*/
}