package com.example.bravedevelopers.presentation.thirdScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.DeleteFromFavoritesUseCase
import com.example.bravedevelopers.domain.useCase.ShowFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdScreenViewModele @Inject constructor(val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
                                                val showFavoritesUseCase: ShowFavoritesUseCase
): ViewModel() {
    val favoritesList = MutableLiveData<List<InformationAboutPokemon>>()
    fun showFavoritesList() {
        favoritesList.postValue(showFavoritesUseCase.showFavorites())
    }

    fun deleteFromFavorites(informationAboutPokemon: InformationAboutPokemon) {
        deleteFromFavoritesUseCase.deleteFromFavorites(informationAboutPokemon)
    }

    init {
        showFavoritesList()
    }
}