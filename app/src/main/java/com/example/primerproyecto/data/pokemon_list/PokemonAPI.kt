package com.example.primerproyecto.data.pokemon_list

import com.example.primerproyecto.data.pokemon_detail.PokemonDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("pokemon?limit=20")
    suspend fun getAllCharacters() : PokemonCharacterDto

    @GET("pokemon/{name}/")
    suspend fun getCharacter(@Path("name") name: String) : PokemonDetailDto

}