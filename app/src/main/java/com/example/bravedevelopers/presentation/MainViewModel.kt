package com.example.bravedevelopers.presentation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bravedevelopers.api.ApiService
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.AddToFavoritesUseCase
import com.example.bravedevelopers.domain.useCase.DeleteFromFavoritesUseCase
import com.example.bravedevelopers.domain.useCase.GetPokemonsListUseCase
import com.example.bravedevelopers.domain.useCase.ShowFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getPokemonsListUseCase: GetPokemonsListUseCase,private val addToFavoritesUseCase: AddToFavoritesUseCase,private val showFavoritesUseCase: ShowFavoritesUseCase ,private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,private val apiService: ApiService): ViewModel()  {

    val favoritesList =MutableLiveData<List<InformationAboutPokemon>>()
    val pokemonsList:MutableLiveData<List<InformationAboutPokemon>> =MutableLiveData()
    var list: MutableList<InformationAboutPokemon> = mutableListOf<InformationAboutPokemon>()
    var url:String="https://pokeapi.co/api/v2/pokemon/"
    val offsetAndOffsetPlusLimit=20..40

    fun loadData2() {
        for (i in offsetAndOffsetPlusLimit) {
            getPokemonsListUseCase.urlOfPokemon("$url$i/")
            getPokemonsListUseCase.execute(onSuccess = {
                pokemonsList.postValue(listOf(it))
                list.add(it)
                addToFavoritesUseCase.addToFavorites(it)
                Log.d("ghj", it.toString())
            }
            )
        }
    }

    fun getFavorites(){
        favoritesList.value= showFavoritesUseCase.showFavorites()
    }

    init {
        getFavorites()
        loadData2()
    }
}


