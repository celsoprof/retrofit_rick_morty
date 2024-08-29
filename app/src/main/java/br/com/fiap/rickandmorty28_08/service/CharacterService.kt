package br.com.fiap.rickandmorty28_08.service

import br.com.fiap.rickandmorty28_08.model.Character
import br.com.fiap.rickandmorty28_08.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

  //https://rickandmortyapi.com/api/character/2

  @GET("character/{id}")
  fun getCharacterById(@Path("id") id: Int): Call<Character>

  @GET("character")
  fun getCharacterList(): Call<Result>

}