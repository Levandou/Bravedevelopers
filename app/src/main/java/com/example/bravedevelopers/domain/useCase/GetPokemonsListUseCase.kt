package com.example.bravedevelopers.domain.useCase

import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.rx.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonsListUseCase @Inject constructor(private val repository: Repository): SingleUseCase<InformationAboutPokemon>() {

    private var pokemonUrl:String?=null

    fun urlOfPokemon(url:String){
        pokemonUrl=url
    }


    override fun buildUseCaseSingle(): Single<InformationAboutPokemon>{
        return repository.getPokemonsList(pokemonUrl)
    }
}