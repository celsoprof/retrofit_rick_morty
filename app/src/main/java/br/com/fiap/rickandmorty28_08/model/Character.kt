package br.com.fiap.rickandmorty28_08.model

import com.google.gson.annotations.SerializedName

data class Character(
  val id: Int = 0,
  @SerializedName("name") val charName: String = "",
  val species: String = "",
  val image: String = "",
  val origin: Origin? = null
)
