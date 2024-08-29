package br.com.fiap.rickandmorty28_08.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

  private val BASE_URL = "https://rickandmortyapi.com/api/"

  private val retrofitFactory = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  fun getCharacterService(): CharacterService{
    return retrofitFactory.create(CharacterService::class.java)
  }



}