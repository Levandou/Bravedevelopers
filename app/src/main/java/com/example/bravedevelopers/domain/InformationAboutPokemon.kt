package com.example.bravedevelopers.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "name_list2")
data class InformationAboutPokemon (
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @PrimaryKey
    @SerializedName("name")
    @Expose
    val name: String = "",

    @SerializedName("base_experience")
    @Expose
    val base_experience:Int? = null,

    @SerializedName("height")
    @Expose
    val height: Int? = null,

    @SerializedName("weight")
    @Expose
    val weight: Int? = null,

    var isFavorite: Boolean=false)