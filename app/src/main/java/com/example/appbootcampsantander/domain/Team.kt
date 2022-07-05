package com.example.appbootcampsantander.domain

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("nome")
    val name: String,
    @SerializedName("forca")
    val start: Int,
    @SerializedName("imagem")
    val image: String,
    var score: Int
)