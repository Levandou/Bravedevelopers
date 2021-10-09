package com.example.bravedevelopers.presentation.thirdScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdScreenFragment : Fragment() {

    private lateinit var adapter: ThirdScreenAdapter
    private val thirdScreenViewModele: ThirdScreenViewModele by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        observeViewModel()


    }


    fun observeViewModel() {

        thirdScreenViewModele.favoritesList.observe(viewLifecycleOwner, {
            adapter.pokemonsListFromDb = it as MutableList<InformationAboutPokemon>
        })
    }


        fun setupRecyclerView(view: View) {
            val rvPokemonsList = view.findViewById<RecyclerView>(R.id.rv_pokemon_list)
            adapter = ThirdScreenAdapter {
                thirdScreenViewModele.deleteFromFavorites(it)
            }
            rvPokemonsList.adapter = adapter
        }
    }
