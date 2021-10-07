package com.example.bravedevelopers.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.GetPokemonsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val getPokemonsListUseCase: GetPokemonsListUseCase)
    : ViewModel()  {

    val pokemonsList: MutableLiveData<List<InformationAboutPokemon>> = MutableLiveData()
    var url:String="https://pokeapi.co/api/v2/pokemon/"
    val offsetAndOffsetPlusLimit=1..20

    fun loadData2()
    {
        for (i in offsetAndOffsetPlusLimit)
        {
            getPokemonsListUseCase.urlOfPokemon("$url$i/")
            getPokemonsListUseCase.execute(onSuccess =
                {
                pokemonsList.value= listOf(it)
                Log.d("ghj", it.toString())
                }
            )
        }
    }
    init
    {
        loadData2()
    }


}