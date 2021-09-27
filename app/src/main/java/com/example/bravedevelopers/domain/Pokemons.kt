package com.example.bravedevelopers.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemons (
    @SerializedName("count")
    @Expose
    val count: Int? = null,

    @SerializedName("next")
    @Expose
    val next: String? = null,

    @SerializedName("previous")
    @Expose
    val previous: Any? = null,

    @SerializedName("results")
    @Expose
    val results: List<Result>? = null)