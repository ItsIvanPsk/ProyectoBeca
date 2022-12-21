package com.example.primerproyecto.data.pokemon

import retrofit2.http.GET

interface PokemonAPI {

    @GET("pokemon?limit=10")
    suspend fun getAllCharacters() : PokemonCharacterDto

}