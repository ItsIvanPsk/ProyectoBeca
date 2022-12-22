package com.example.primerproyecto.data.pokemon

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon?limit=20")
    suspend fun getAllCharacters() : PokemonCharacterDto

    @GET("pokemon/{name}/")
    suspend fun getCharacter(@Path("name") name: String) : PokemonDetailDto

}