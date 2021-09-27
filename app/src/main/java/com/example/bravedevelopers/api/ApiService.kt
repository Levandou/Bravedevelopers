package com.example.bravedevelopers.api

import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.Pokemons
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {


    @GET("pokemon/")
    fun getPokemons(
        @Query(QUERY_PARAM_LIMIT) limit :Int=20,
        @Query(QUERY_PARAM_OFFSET) offset :Int=0
    ): Single<Pokemons>
    @GET()
    fun getInformationAboutPokemon(
        @Url()url:String
    ): Single<InformationAboutPokemon>

    fun getNumber(number: String):String  {return number}
    fun getOffset(): String {return QUERY_PARAM_OFFSET}

    companion object{
        const val QUERY_PARAM_ID="id"
        const val QUERY_PARAM_LIMIT="limit"
        const val QUERY_PARAM_OFFSET="offset"
    }


}