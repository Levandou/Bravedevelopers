package com.example.bravedevelopers.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result (
    //  @PrimaryKey
    @SerializedName("name")
    @Expose
    val name: String ="",
    @SerializedName("url")
    @Expose
    val url: String? = null
)