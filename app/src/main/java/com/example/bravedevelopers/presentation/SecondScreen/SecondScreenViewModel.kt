package com.example.bravedevelopers.presentation.SecondScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.AddToFavoritesUseCase
import com.example.bravedevelopers.domain.useCase.DeleteFromFavoritesUseCase
import com.example.bravedevelopers.domain.useCase.GetPokemonsListUseCase
import com.example.bravedevelopers.domain.useCase.ShowFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getPokemonsListUseCase: GetPokemonsListUseCase,
    private val showFavoritesUseCase: ShowFavoritesUseCase
): ViewModel() {


    val favoritesList = MutableLiveData<List<InformationAboutPokemon>>()
    val pokemonsList: MutableLiveData<List<InformationAboutPokemon>> = MutableLiveData()
    var url: String = "https://pokeapi.co/api/v2/pokemon/"
    val offsetAndOffsetPlusLimit: IntRange = 1..20
    var a:Int=0
    var list: MutableList<InformationAboutPokemon> = mutableListOf()

    fun loadData2() {
        for (i in offsetAndOffsetPlusLimit) {
            getPokemonsListUseCase.urlOfPokemon("$url$i/")
            getPokemonsListUseCase.execute(onSuccess = {
                //pokemonsList.postValue(listOf(it))//
                pokemonsList.value = listOf(it)
                list.add(it)
                //    addToFavoritesUseCase.addToFavorites(it)
                Log.d("ghj", it.toString())
            }
            )
        }
        Log.d("lkio", list.toString())
        //   pokemonsList.postValue(list)
    }

    fun showFavoritesList() {
        favoritesList.postValue(showFavoritesUseCase.showFavorites())

    }

    fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon) {
        deleteFromFavoritesUseCase.deleteFromFavorites(informationAboutPokemon)
    }

    fun addToFavorites(informationAboutPokemon: InformationAboutPokemon) {
        addToFavoritesUseCase.addToFavorites(informationAboutPokemon)
    }

    init {

        loadData2()
        showFavoritesList()
    }
}